package com.example.network

import com.example.network.model.ListEvents


/**
 *Created by Ubaid Khaliq
 */
interface NetworkRepository {
    suspend fun getAllEvents(): ResponseWrapper<ListEvents>
}