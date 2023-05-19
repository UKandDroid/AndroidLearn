package test.app.domain.repo

import com.example.core.Event
import com.example.core.Image
import com.example.core.message.EventEntity
import com.example.core.message.EventModel
import com.example.core.room.EventDao
import com.example.network.NetworkRepository
import com.example.network.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val messageDao: EventDao, private  val networkRepository: NetworkRepository)
    : LocalRepository {

    override suspend fun getAllEvents(): Flow<List<EventModel>> {
        return messageDao.getEvents()
    }

    override suspend fun refreshEvents(): Boolean {
       val result = networkRepository.getAllEvents()
        when(result){
        is ResponseWrapper.ApiError -> return false
            is ResponseWrapper.ApiSuccess -> {
                saveEvents(result.data.events)
                return true
            }
        }
    }

    override suspend fun saveEvents(events: List<Event>) {
        withContext(Dispatchers.IO){
        messageDao.putEvent(
            events.map { EventEntity(name = it.name, desc = it.locale, url = it.images.firstOrEmpty()) }
        )
        }
    }
}

private fun  List<Image>.firstOrEmpty() = if (isNotEmpty()) first().url else ""

