package com.example.network

import com.example.core.ChatMessage
import com.example.core.UserChat
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *Created by Ubaid Khaliq
 */
interface RemoteApi {

    @GET("/public/v2/users")
    suspend fun getAllMessages(): Response<List<UserChat>>

    @GET("/public/v2/users/{userId}")
    suspend fun getMessage(@Path("userId") userId: String): Response<UserChat>
}