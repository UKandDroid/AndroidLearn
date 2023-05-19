package test.app.domain.repo

import com.example.core.Event
import com.example.core.message.EventEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun getAllEvents(): Flow<List<EventEntity>>

    suspend fun saveEvents(events: List<Event>)

    suspend fun refreshEvents() : Boolean
}