package com.example.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.entity.EventEntity

@Database(entities = [EventEntity::class], version = 1)
abstract class LocalCache: RoomDatabase() {

    abstract fun getMessageDao(): EventDatabase
}