package test.app.domain.repo

import com.example.network.model.Photo
import com.example.core.entity.PhotoEntity
import com.example.core.entity.EventModel
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

    override suspend fun getEvents(name: String): List<PhotoEntity> =
        withContext(Dispatchers.IO){ eventsDb.getEvents(name) }

    override suspend fun refreshEvents(): Boolean {
        val result = networkRepository.getAllPhotos()

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

    override suspend fun saveEvents(listPhotos: List<Photo>) {
        eventsDb.putEvent(
            listPhotos.map { PhotoEntity(name = it.title, desc = it.description, url = it.url) })
        }

}


