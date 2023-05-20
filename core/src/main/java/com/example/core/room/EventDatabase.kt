package com.example.core.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core.entity.EventEntity

@Dao
interface EventDatabase {

    @Query("SELECT * FROM events")
    fun getEvents(): List<EventEntity>

    @Query("SELECT * FROM events WHERE name LIKE '%' ||:name ||'%'")
    fun getEvents(name: String): List<EventEntity>

    @Query("DELETE FROM events")
    fun deleteAllRows();

    @Insert
    fun putEvent(entity: EventEntity)

    @Insert
    fun putEvent(entity: List<EventEntity>)

}