package com.example.network

import com.example.core.UserChat
import java.lang.Exception


/**
 *Created by Ubaid Khaliq
 */

class NetworkRepositoryImpl constructor(val remoteApi: RemoteApi) : NetworkRepository {
    private val EXCEPTION: Int = -1

    override suspend fun getAllMessages(): ResponseWrapper<List<UserChat>> {
        return try {
            val response = remoteApi.getEvents()
            val result = response.body()
            if (response.isSuccessful && result != null) {
                ResponseWrapper.Success(result)
            } else {
                ResponseWrapper.Error(response.code(), response.message())
            }
        } catch (e: Exception) {
            println("Error: $e")
            ResponseWrapper.Error(EXCEPTION, "An $e occurred")
        }
    }


}