package test.app.ui.home

import test.app.domain.model.ui.InfoItem
import test.app.domain.model.ui.ScreenListItem
sealed class UiStates{
    abstract val items: List<ScreenListItem>

    data class ListEvents( override val items: List<ScreenListItem>): UiStates()

    data class EventsUpdate(val message: String): UiStates(){
        override val items: List<ScreenListItem> = listOf(InfoItem(message))
    }
}

