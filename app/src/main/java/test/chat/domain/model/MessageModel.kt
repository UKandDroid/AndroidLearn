package test.chat.domain.model

data class MessageModel(
    val text: String,
    val main: Boolean,
    val timeInMillis: Long
)
