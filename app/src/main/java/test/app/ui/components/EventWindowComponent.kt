package test.app.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import test.app.domain.model.ui.ScreenListItem
import test.app.domain.model.ui.EventItem


@SuppressLint("SuspiciousIndentation")
@Composable
fun EventWindowComponent(
    scrollPos : Int,
    events: List<ScreenListItem>,
    onSearchClick: (String) -> Unit
) {

    val chatListState = rememberLazyListState()
    val scrollPosition = remember(scrollPos) { derivedStateOf {  scrollPos  } }

        Column {
            SearchComponent(
                modifier = Modifier.fillMaxWidth(),
                onSearch = onSearchClick
                )

            EventListComponent(
                modifier = Modifier
                    .weight(1.0f)
                    .fillMaxHeight()
                    .background(Color.White),
                eventList = events,
                listState = chatListState
            )


    }


    LaunchedEffect(key1 = scrollPosition,key2 = events.size) {
        chatListState.animateScrollToItem(scrollPosition.value)
    }

}


@Preview
@Composable
fun PreviewChat(){
    EventWindowComponent( 0,events = listOf(
        EventItem("Hi there", "true", "true"),
        EventItem("ohh hello", "false", "true"),
        EventItem("Sorry wrong number ", "true", "true"),

        ) , {})
}