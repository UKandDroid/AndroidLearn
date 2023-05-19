package com.example.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.message.EventEntity

@Database(entities = [EventEntity::class], version = 1)
abstract class MessageDatabase: RoomDatabase() {

    abstract fun getMessageDao(): EventDao
}