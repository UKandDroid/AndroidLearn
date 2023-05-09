package test.app.ui.home

import test.app.domain.model.ui.ChatItem


data class ChatUiState(
    val scrollTo: Int,
    val chatItems: List<ChatItem>,
)