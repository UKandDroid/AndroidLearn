package test.app.domain.model.ui

sealed class ScreenListItem
data class MessageItem(val message: String): ScreenListItem()
data class EventItem(val name: String,
                     val desc: String,
                     var url: String,
                       ): ScreenListItem()