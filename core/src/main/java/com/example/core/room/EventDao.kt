package com.example.core.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core.message.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Query("SELECT * FROM events")
    fun getEvents(): Flow<List<EventEntity>>

    @Query("DELETE FROM events")
    fun deleteAllRows();

    @Insert
    fun putEvent(entity: EventEntity)

}