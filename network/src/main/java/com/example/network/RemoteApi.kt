package com.example.network

import com.example.network.model.EventResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 *Created by Ubaid Khaliq
 */
interface RemoteApi {

    @GET("/discovery/v2/events.json")
    suspend fun getEvents(): Response<EventResponse>

}