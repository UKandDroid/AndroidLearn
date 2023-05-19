package test.app.ui.home

import test.app.domain.model.ui.MessageItem
import test.app.domain.model.ui.ScreenListItem
sealed class  UiStates
data class ListEvents(val items: List<ScreenListItem>): UiStates()
data class EventsLoading(val items: List<ScreenListItem> = listOf(MessageItem("Loading..."))): UiStates()
