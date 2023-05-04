package test.app.domain.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import test.app.data.message.MessageEntity

@Dao
interface MessageDao {

    @Query("SELECT * FROM messages ORDER BY send_time ASC")
    fun getMessages(): Flow<List<MessageEntity>>

    @Query("DELETE FROM messages")
    fun deleteAllRows();

    @Insert
    fun putMessage(entity: MessageEntity)

}