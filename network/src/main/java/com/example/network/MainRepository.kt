package com.example.network

import com.example.core.Message
import com.example.core.UserChat


/**
 *Created by Ubaid Khaliq
 */
interface MainRepository {
    suspend fun getAllMessages(): ResponseWrapper<List<UserChat>>
    suspend fun getMessage(id: String): ResponseWrapper<UserChat>
}