package test.chat.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import test.chat.data.model.MessageEntity

@Dao
interface MessageDao {

    @Query("SELECT * FROM messages ORDER BY send_time ASC")
    fun getMessages(): Flow<List<MessageEntity>>

    @Query("DELETE FROM messages")
    fun deleteAllRows();

    @Insert
    fun putMessage(entity: MessageEntity)

}