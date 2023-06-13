package test.app.domain.repo

import com.example.network.model.Photo
import com.example.core.entity.PhotoEntity

interface LocalRepository {

    suspend fun getAllPhotos(): List<PhotoEntity>

    suspend fun getPhotoByTitle(title: String): List<PhotoEntity>

    suspend fun savePhoto(listPhoto: List<Photo>)

    suspend fun refreshPhotos() : Boolean
}