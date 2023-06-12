package com.example.network

import com.example.network.model.PhotosResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 *Created by Ubaid Khaliq
 */
interface RemoteApi {

    @GET("/v1/sample-data/photos")
    suspend fun getPhotos(): Response<PhotosResponse>

    @GET("/v1/sample-data/photos")
    suspend fun getAllPhotos(): Response<PhotosResponse>
}