package test.chat.ui.home

import test.chat.domain.model.ui.ChatItem


data class ChatUiState(
    val chatItems: List<ChatItem>,
)