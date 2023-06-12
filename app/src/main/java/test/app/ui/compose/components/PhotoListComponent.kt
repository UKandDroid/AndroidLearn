package test.app.ui.compose.components

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import test.app.domain.model.ui.ScreenListItem
import test.app.domain.model.ui.EventItem
import test.app.domain.model.ui.MessageItem

@Composable
fun EventListComponent(
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

        items(items = eventList) { event ->
            when (event) {
                is EventItem -> {
                    EventItemComponent(
                        name = event.name,
                        desc = event.desc,
                        imageUrl = event.url,
                    )
                }

                is MessageItem -> {
                    MessageItemComponent(title = event.message)
                }
            }
        }
    }

}