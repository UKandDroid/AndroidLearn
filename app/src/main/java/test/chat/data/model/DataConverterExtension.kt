package test.chat.data.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import test.chat.domain.model.MessageModel

fun MessageEntity.convert(): MessageModel {
    return MessageModel(this.text, this.main, this.sendTime)
}

fun List<MessageEntity>.convert(): List<MessageModel> {
    return map { it.convert() }
}

fun Flow<List<MessageEntity>>.convert(): Flow<List<MessageModel>> {
    return map { it.convert() }
}