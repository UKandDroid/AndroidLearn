package test.app.ui.components

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import test.app.domain.model.ui.ScreenListItem
import test.app.domain.model.ui.EventItem
import test.app.domain.model.ui.SectionItem

@Composable
fun ChatListComponent(
    modifier: Modifier,
    eventList: List<ScreenListItem>,
    listState: LazyListState,
) {
    LazyColumn(
        state = listState,
        modifier = modifier,
        reverseLayout = true,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        items(items = eventList.asReversed()) { chatItem ->
            when (chatItem) {
                is EventItem -> {
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