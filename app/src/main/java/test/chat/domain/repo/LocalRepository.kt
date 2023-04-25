package test.chat.domain.repo

import kotlinx.coroutines.flow.Flow
import test.chat.domain.model.MessageModel

interface LocalRepository {

    fun getAllMessages(): Flow<List<MessageModel>>

    fun saveMessage(message: String, main: Boolean)
}