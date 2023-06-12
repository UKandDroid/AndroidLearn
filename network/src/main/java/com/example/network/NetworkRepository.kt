package com.example.network

import com.example.network.model.ListPhotos


/**
 *Created by Ubaid Khaliq
 */
interface NetworkRepository {
    suspend fun getAllPhotos(): ResponseWrapper<ListPhotos>
}