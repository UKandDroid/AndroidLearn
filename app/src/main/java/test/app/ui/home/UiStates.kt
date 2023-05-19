package test.app.ui.home

import test.app.domain.model.ui.MessageItem
import test.app.domain.model.ui.ScreenListItem
sealed class UiStates{
    abstract val items: List<ScreenListItem>

    class ListEvents( override val items: List<ScreenListItem>): UiStates()

    class EventsUpdate(message: String): UiStates(){
        override val items: List<ScreenListItem> = listOf(MessageItem(message))
    }
}

