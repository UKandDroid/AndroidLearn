package test.app.domain.repo

import com.example.core.message.EventEntity
import com.example.core.message.EventModel
import com.example.core.room.EventDao
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val messageDao: EventDao)
    : LocalRepository {

    override fun getAllEvents(): Flow<List<EventModel>> {
        return messageDao.getEvents()
    }

    override fun refreshEvents(): Boolean {
        TODO("Not yet implemented")
    }


    override fun saveEvent(name: String, desc: String, url : String) {

        messageDao.putEvent(
            EventEntity(
                name = name,
                desc = desc,
                url = url
            )
        )
    }
}