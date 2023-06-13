package test.app.domain.model

sealed class ScreenListItem
data class InfoItem(val message: String): ScreenListItem()
data class PhotoItem(val name: String,
                     val desc: String,
                     var url: String,
                       ): ScreenListItem()