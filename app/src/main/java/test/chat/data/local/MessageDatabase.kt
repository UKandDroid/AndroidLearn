package test.chat.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import test.chat.data.model.MessageEntity

@Database(entities = [MessageEntity::class], version = 1)
abstract class MessageDatabase: RoomDatabase() {

    abstract fun getMessageDao(): MessageDao
}