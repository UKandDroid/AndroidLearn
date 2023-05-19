package com.example.network

import com.example.core.UserChat


/**
 *Created by Ubaid Khaliq
 */
interface NetworkRepository {
    suspend fun getAllMessages(): ResponseWrapper<List<UserChat>>
}