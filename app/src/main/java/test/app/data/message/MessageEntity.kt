package test.app.data.message

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "userMain") val main: Boolean,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "send_time") val sendTime: Long
)