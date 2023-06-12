package test.app.domain.repo

import com.example.network.model.Photo
import com.example.core.entity.PhotoEntity

interface LocalRepository {

    suspend fun getAllEvents(): List<PhotoEntity>

    suspend fun getEvents(name: String): List<PhotoEntity>

    suspend fun saveEvents(events: List<Photo>)

    suspend fun refreshEvents() : Boolean
}