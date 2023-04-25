package com.example.core

sealed class Message
data class ChatMessage(val time: Long, val message: String) : Message()
data class UserChat(val list: List<ChatMessage>) : Message()
