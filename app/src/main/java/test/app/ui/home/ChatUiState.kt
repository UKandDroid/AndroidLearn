package test.app.ui.home

import test.app.domain.model.ui.ChatItem


data class ChatUiState(
    val chatItems: List<ChatItem>,
)