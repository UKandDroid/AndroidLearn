package test.app.ui.home

import test.app.domain.model.InfoItem
import test.app.domain.model.ScreenListItem
sealed class UiStates{
    abstract val items: List<ScreenListItem>

    data class ListPhotos(override val items: List<ScreenListItem>): UiStates()

    data class PhotosUpdate(val message: String): UiStates(){
        override val items: List<ScreenListItem> = listOf(InfoItem(message))
    }
}

