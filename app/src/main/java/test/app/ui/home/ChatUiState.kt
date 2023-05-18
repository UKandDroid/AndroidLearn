package test.app.ui.home

import test.app.domain.model.ui.ScreenListItem


data class ChatUiState(
    val scrollTo: Int,
    val chatItems: List<ScreenListItem>,
)