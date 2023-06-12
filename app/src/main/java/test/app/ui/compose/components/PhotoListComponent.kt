package test.app.ui.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import test.app.domain.model.ui.PhotoItem
import test.app.domain.model.ui.InfoItem
import test.app.domain.model.ui.ScreenListItem

@Composable
fun EventListComponent(
    modifier: Modifier,
    photoList: List<ScreenListItem>,
) {
    val listState = rememberLazyListState()
    val scrollToTop = remember(key1 = photoList.size) { mutableStateOf(photoList.size) }
    LaunchedEffect(key1 = scrollToTop) { listState.scrollToItem(photoList.size) }

    LazyColumn(
        state = listState,
        modifier = modifier,
        reverseLayout = true,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        items(items = photoList) { event ->
            when (event) {
                is PhotoItem -> {
                    EventItemComponent(
                        name = event.name,
                        desc = event.desc,
                        imageUrl = event.url,
                    )
                }

                is InfoItem -> {
                    MessageItemComponent(title = event.message)
                }
            }
        }
    }



}