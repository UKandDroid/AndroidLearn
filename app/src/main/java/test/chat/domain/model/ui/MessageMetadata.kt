package test.chat.domain.model.ui

data class MessageMetadata(
    val time: Long,
    val displayDate: MessageDate,
    val main: Boolean
)