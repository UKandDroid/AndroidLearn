package test.chat.domain.model

import android.annotation.SuppressLint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import test.chat.domain.model.ui.Message
import test.chat.domain.model.ui.MessageDate
import test.chat.domain.model.ui.MessageMetadata
import java.text.SimpleDateFormat
import java.util.*


fun MessageModel.convert(): Message {
    return Message(
        text = text,
        metadata = MessageMetadata(
            time = timeInMillis,
            displayDate = timeInMillis.toDisplayableDate(),
            main = main
        )
    )
}

fun Flow<List<MessageModel>>.convert(): Flow<List<Message>> {
    return map { flow -> flow.map { it.convert() } }
}

@SuppressLint("SimpleDateFormat")
fun Long.toDisplayableDate(): MessageDate {
    val date = Date(this)
    return SimpleDateFormat("EEEE,HH,mm").also {
        it.timeZone = TimeZone.getTimeZone("UTC")
    }.format(date).split(",").takeIf { it.size == 3 }?.let {
        MessageDate(day = it[0], hour = it[1], minute = it[2])
    } ?: MessageDate("", "", "")
}