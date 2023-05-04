package test.app.domain.model.ui

sealed class ChatItem
data class SectionItem(val title: String): ChatItem()
data class MessageItem(val text: String,
                       val isUser: Boolean,
                       var hasTail: Boolean): ChatItem()