package test.app.domain.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.core.message.MessageEntity
import test.app.domain.model.MessageModel

fun com.example.core.message.MessageEntity.convert(): MessageModel {
    return MessageModel(this.text, this.main, this.sendTime)
}

fun List<com.example.core.message.MessageEntity>.convert(): List<MessageModel> {
    return map { it.convert() }
}

fun Flow<List<com.example.core.message.MessageEntity>>.convert(): Flow<List<MessageModel>> {
    return map { it.convert() }
}