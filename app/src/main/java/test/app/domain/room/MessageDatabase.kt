package test.app.domain.room

import androidx.room.Database
import androidx.room.RoomDatabase
import test.app.data.message.MessageEntity

@Database(entities = [MessageEntity::class], version = 1)
abstract class MessageDatabase: RoomDatabase() {

    abstract fun getMessageDao(): MessageDao
}