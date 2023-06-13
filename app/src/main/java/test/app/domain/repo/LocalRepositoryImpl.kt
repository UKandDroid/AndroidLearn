package test.app.domain.repo

import com.example.network.model.Photo
import com.example.core.entity.PhotoEntity
import com.example.core.entity.PhotoModel
import com.example.core.room.PhotoDatabase
import com.example.network.NetworkRepository
import com.example.network.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dbRepository: PhotoDatabase,
    private val networkRepository: NetworkRepository)
    : LocalRepository {

    override suspend fun getAllPhotos(): List<PhotoModel>  =
        withContext(Dispatchers.IO){ dbRepository.getPhotos() }

    override suspend fun getPhotoByTitle(title: String): List<PhotoEntity> =
        withContext(Dispatchers.IO){ dbRepository.getPhotos(title) }

    override suspend fun refreshPhotos(): Boolean {
        val result = networkRepository.getAllPhotos()

        return when(result){
            is ResponseWrapper.ApiError ->  false
            is ResponseWrapper.ApiSuccess -> {
                withContext(Dispatchers.IO){
                    dbRepository.deleteAllRows()
                    savePhoto(result.data.listPhotos)
                }
                true
            }
        }
    }

    override suspend fun savePhoto(listPhotos: List<Photo>) {
        dbRepository.putPhoto(
            listPhotos.map { PhotoEntity(title = it.title, desc = it.description, url = it.url) })
        }

}


