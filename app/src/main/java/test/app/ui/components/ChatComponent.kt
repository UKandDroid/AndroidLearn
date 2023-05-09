package test.app.ui.components

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import test.app.domain.model.ui.ChatItem
import test.app.domain.model.ui.MessageItem
import test.app.domain.model.ui.SectionItem

@Composable
fun ChatListComponent(
    modifier: Modifier,
    chatListItems: List<ChatItem>,
    listState: LazyListState,
) {
    LazyColumn(
        state = listState,
        modifier = modifier,
        reverseLayout = true,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        itemsIndexed(items = chatListItems.asReversed()) { index,chatItem ->
            when (chatItem) {
                is MessageItem -> {
                    MessageWithBg(
                        text = chatItem.text,
                        isUser = chatItem.isUser,
                        hasTail = chatItem.hasTail,
                        highlight = chatItem.highlight,
                        modifier = Modifier.defaultMinSize(minWidth = 30.dp)
                    )
                }

                is SectionItem -> {
                    SectionItemComponent(title = chatItem.title)
                }
            }
        }

        item {
            SectionItemComponent("This is the start of your chat!")
        }
    }

}