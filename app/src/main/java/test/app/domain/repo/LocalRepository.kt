package test.app.domain.repo

import com.example.core.Event
import com.example.core.message.EventEntity

interface LocalRepository {

    suspend fun getAllEvents(): List<EventEntity>

    suspend fun getEvents(name: String): List<EventEntity>

    suspend fun saveEvents(events: List<Event>)

    suspend fun refreshEvents() : Boolean
}