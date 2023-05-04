package test.app.domain.repo

import kotlinx.coroutines.flow.Flow
import test.app.domain.model.MessageModel

interface LocalRepository {

    fun getAllMessages(): Flow<List<MessageModel>>

    fun saveMessage(message: String, main: Boolean)
}