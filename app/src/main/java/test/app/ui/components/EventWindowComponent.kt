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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.Event
import com.example.core.message.EventEntity
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import test.app.domain.model.ui.ScreenListItem
import test.app.domain.model.ui.EventItem
import test.app.domain.repo.LocalRepository
import test.app.ui.home.EventViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun EventWindowComponent(
    events: List<ScreenListItem>,
    onSearchClick: (String) -> Unit,
    viewModel: EventViewModel
) {

    val chatListState = rememberLazyListState()
   // val viewModel = viewModel<EventViewModel>()
    val isLoading by viewModel.isLoading
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

        Column {
            SearchComponent(
                modifier = Modifier.fillMaxWidth(),
                onSearch = onSearchClick
                )

            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = viewModel::refreshEvents){
                EventListComponent(
                    modifier = Modifier
                        .weight(1.0f)
                        .fillMaxHeight()
                        .background(Color.White),
                    eventList = events,
                    listState = chatListState
                )
            }
    }


    LaunchedEffect(key1 = events.size) {
        chatListState.animateScrollToItem(0)
    }

}


@Preview
@Composable
fun PreviewChat(){
    EventWindowComponent( events = listOf(
        EventItem("Hi there", "true", "true"),
        EventItem("ohh hello", "false", "true"),
        EventItem("Sorry wrong number ", "true", "true"),

        ) , {}, viewModel = EventViewModel(object :LocalRepository{
        override suspend fun getAllEvents(): List<EventEntity> {
            TODO("Not yet implemented")
        }

        override suspend fun getEvents(name: String): List<EventEntity> {
            TODO("Not yet implemented")
        }

        override suspend fun saveEvents(events: List<Event>) {
            TODO("Not yet implemented")
        }

        override suspend fun refreshEvents(): Boolean {
            TODO("Not yet implemented")
        }
    })
    )
}