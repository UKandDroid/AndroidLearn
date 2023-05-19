package com.example.network

import com.example.core.UserChat
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *Created by Ubaid Khaliq
 */
interface RemoteApi {

    @GET("/discovery/v2/events.json?apikey={apikey}")
    suspend fun getEvents(): Response<List<UserChat>>

}