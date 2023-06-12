package com.example.network

import com.example.network.model.ListPhotos
import java.lang.Exception


/**
 *Created by Ubaid Khaliq
 */

class NetworkRepositoryImpl constructor(val remoteApi: RemoteApi) : NetworkRepository {
    private val EXCEPTION: Int = -1

    override suspend fun getAllPhotos(): ResponseWrapper<ListPhotos> {
        return try {
            val response = remoteApi.getAllPhotos()
            val result = response.body()
            if (response.isSuccessful && result != null) {
                ResponseWrapper.ApiSuccess(ListPhotos(result.photos))
            } else {
                ResponseWrapper.ApiError(response.code(), response.message())
            }
        } catch (e: Exception) {
            println("Error: $e")
            ResponseWrapper.ApiError(EXCEPTION, "An $e occurred")
        }
    }


}