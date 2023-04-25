package com.example.network

import com.example.core.ChatMessage
import com.example.core.UserChat
import com.example.core.Message
import java.lang.Exception


/**
 *Created by Ubaid Khaliq
 */

class MainRepositoryImpl constructor(val remoteApi: RemoteApi) : MainRepository {
    private val EXCEPTION: Int = -1

    override suspend fun getAllMessages(): ResponseWrapper<List<UserChat>> {
        return try {
            val response = remoteApi.getAllMessages()
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


    override suspend fun getMessage(id: String): ResponseWrapper<UserChat> {
        return try {
            val response = remoteApi.getMessage(id)

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