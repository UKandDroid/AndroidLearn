package com.example.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.entity.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1)
abstract class LocalCache: RoomDatabase() {

    abstract fun getMessageDao(): PhotoDatabase
}