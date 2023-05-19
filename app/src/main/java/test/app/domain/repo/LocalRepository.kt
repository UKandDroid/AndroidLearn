package test.app.domain.repo

import com.example.core.message.EventEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getAllEvents(): Flow<List<EventEntity>>

    fun saveEvent(name: String, url: String, desc: String)

    fun refreshEvents() : Boolean
}