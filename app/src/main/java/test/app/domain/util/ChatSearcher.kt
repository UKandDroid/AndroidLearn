package test.app.domain.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import test.app.domain.model.ui.ChatItem
import test.app.domain.model.ui.MessageItem
import javax.inject.Inject

class ChatSearcher @Inject constructor() {

    suspend fun search(list: List<ChatItem>, searchText: String): Flow<List<ChatItem>> {
        list.map {
            if (it is MessageItem) {
                it.highlight = if (searchText.isNotEmpty())
                    it.text.trim().startsWith(searchText)
                else false
            }
        }

        return flowOf(list)
    }
}