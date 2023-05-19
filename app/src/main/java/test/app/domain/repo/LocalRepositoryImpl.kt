package test.app.domain.repo

import com.example.core.Event
import com.example.core.Image
import com.example.core.message.EventEntity
import com.example.core.message.EventModel
import com.example.core.room.EventDatabase
import com.example.network.NetworkRepository
import com.example.network.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val eventsDb: EventDatabase,
    private val networkRepository: NetworkRepository)
    : LocalRepository {

    override suspend fun getAllEvents(): List<EventModel>  =
        withContext(Dispatchers.IO){ eventsDb.getEvents() }

    override suspend fun getEvents(name: String): List<EventEntity> =
        withContext(Dispatchers.IO){ eventsDb.getEvents(name) }


    override suspend fun refreshEvents(): Boolean {
        val result = networkRepository.getAllEvents()

        return when(result){
            is ResponseWrapper.ApiError ->  false
            is ResponseWrapper.ApiSuccess -> {
                withContext(Dispatchers.IO){
                    eventsDb.deleteAllRows()
                    saveEvents(result.data.events)
                }
                true
            }
        }
    }

    override suspend fun saveEvents(events: List<Event>) {
        eventsDb.putEvent(
            events.map { EventEntity(name = it.name, desc = it.desc, url = it.images.firstOrEmpty()) })
        }

}

private fun  List<Image>.firstOrEmpty() = if (isNotEmpty()) first().url else ""

