package test.chat.data.repo

import kotlinx.coroutines.flow.Flow
import test.chat.data.local.MessageDao
import test.chat.data.model.MessageEntity
import test.chat.data.model.convert
import test.chat.domain.model.MessageModel
import test.chat.domain.repo.LocalRepository

import javax.inject.Inject

class RoomCacheRepositoryImpl @Inject constructor(private val messageDao: MessageDao)
    : LocalRepository {

    override fun getAllMessages(): Flow<List<MessageModel>> {
        return messageDao.getMessages().convert()
    }


    override fun saveMessage(message: String, main: Boolean) {

        messageDao.putMessage(
            MessageEntity(main = main,
                text = message,
                sendTime = System.currentTimeMillis())
        )
    }
}