package test.app.domain.model.ui

sealed class ScreenListItem
data class SectionItem(val title: String): ScreenListItem()
data class EventItem(val text: String,
                     val isUser: Boolean,
                     var hasTail: Boolean,
                     var highlight: Boolean = false
                       ): ScreenListItem()