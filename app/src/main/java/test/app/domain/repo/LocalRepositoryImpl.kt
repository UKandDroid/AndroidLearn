package test.app.domain.repo

import kotlinx.coroutines.flow.Flow
import test.app.domain.room.MessageDao
import test.app.data.message.MessageEntity
import test.app.domain.util.convert
import test.app.domain.model.MessageModel

import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val messageDao: MessageDao)
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