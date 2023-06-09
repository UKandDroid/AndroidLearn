package test.app.domain.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import test.app.domain.model.ui.ChatItem
import test.app.domain.model.ui.Message
import test.app.domain.model.ui.MessageItem
import test.app.domain.model.ui.SectionItem
import test.app.domain.repo.LocalRepository

import javax.inject.Inject

class ChatConvertor  @Inject constructor(){

    fun convertChat( cache: LocalRepository): Flow<List<ChatItem>> {
        return cache.getAllMessages().convert().map {
            it.convert()
        }
    }

    private fun List<Message>.convert(): List<ChatItem> {

        val chatList = mutableListOf<ChatItem>()

        forEachIndexed { index, message ->
            val previousMessage = this.getOrNull(index - 1)
            val nextMessage = this.getOrNull(index + 1)

            var hasTail = false

            if (previousMessage == null ||
                message.calculateTimeDiff(previousMessage) > HOUR
            ) {
                chatList.add(
                    SectionItem(message.metadata.displayDate.toString())
                )
            }

            nextMessage?.let {
                if (it.metadata.main != message.metadata.main) hasTail = true
                if (it.calculateTimeDiff(message) > TWENTY_SECONDS) hasTail = true
            }

            if (index == lastIndex) hasTail = true

            chatList.add(
                MessageItem(message.text, isUser = message.metadata.main, hasTail, false)
            )
        }

        return chatList
    }

    private fun Message.calculateTimeDiff(other: Message): Long {
        return this.metadata.time - other.metadata.time
    }

    companion object {
        private const val SECOND = 1000L
        private const val MINUTE = 60 * SECOND
        private const val HOUR = 60 * MINUTE
        private const val TWENTY_SECONDS = 20 * SECOND
    }
}