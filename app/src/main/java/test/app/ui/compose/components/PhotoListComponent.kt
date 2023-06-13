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
import test.app.domain.model.PhotoItem
import test.app.domain.model.InfoItem
import test.app.domain.model.ScreenListItem

@Composable
fun PhotoListComponent(
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

        items(items = photoList) { photo ->
            when (photo) {
                is PhotoItem -> {
                    PhotoItemComponent(
                        name = photo.name,
                        desc = photo.desc,
                        imageUrl = photo.url,
                    )
                }

                is InfoItem -> {
                    MessageItemComponent(title = photo.message)
                }
            }
        }
    }



}