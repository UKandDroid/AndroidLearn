package com.example.core.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core.message.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Query("SELECT * FROM messages ORDER BY send_time ASC")
    fun getMessages(): Flow<List<MessageEntity>>

    @Query("DELETE FROM messages")
    fun deleteAllRows();

    @Insert
    fun putMessage(entity: MessageEntity)

}