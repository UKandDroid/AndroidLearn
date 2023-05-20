package com.example.network

import com.example.network.model.ListEvents
import java.lang.Exception


/**
 *Created by Ubaid Khaliq
 */

class NetworkRepositoryImpl constructor(val remoteApi: RemoteApi) : NetworkRepository {
    private val EXCEPTION: Int = -1

    override suspend fun getAllEvents(): ResponseWrapper<ListEvents> {
        return try {
            val response = remoteApi.getEvents()
            val result = response.body()
            if (response.isSuccessful && result != null) {
                ResponseWrapper.ApiSuccess(ListEvents(result._embedded.events))
            } else {
                ResponseWrapper.ApiError(response.code(), response.message())
            }
        } catch (e: Exception) {
            println("Error: $e")
            ResponseWrapper.ApiError(EXCEPTION, "An $e occurred")
        }
    }


}