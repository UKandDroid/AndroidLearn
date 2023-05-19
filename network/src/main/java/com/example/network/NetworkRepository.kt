package com.example.network

import com.example.core.ListEvents


/**
 *Created by Ubaid Khaliq
 */
interface NetworkRepository {
    suspend fun getAllEvents(): ResponseWrapper<ListEvents>
}