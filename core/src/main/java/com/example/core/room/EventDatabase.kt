package com.example.core.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core.entity.PhotoEntity

@Dao
interface EventDatabase {

    @Query("SELECT * FROM events")
    fun getEvents(): List<PhotoEntity>

    @Query("SELECT * FROM events WHERE name LIKE '%' ||:name ||'%' ")
    fun getEvents(name: String): List<PhotoEntity>

    @Query("DELETE FROM events")
    fun deleteAllRows();

    @Insert
    fun putEvent(entity: PhotoEntity)

    @Insert
    fun putEvent(entity: List<PhotoEntity>)

}