package test.app.ui.compose.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import test.app.domain.model.ui.ScreenListItem
import test.app.domain.model.ui.PhotoItem


@SuppressLint("SuspiciousIndentation")
@Composable
fun EventWindowComponent(
    isLoading: Boolean,
    listPhotos: List<ScreenListItem>,
    onSearchClick: (String) -> Unit,
    onRefresh: () -> Unit,
) {


    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

        Column {
            SearchComponent(
                modifier = Modifier.fillMaxWidth(),
                onSearch = onSearchClick
                )

            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = onRefresh){
                EventListComponent(
                    modifier = Modifier
                        .weight(1.0f)
                        .fillMaxHeight()
                        .background(Color.White),
                    photoList = listPhotos
                )
            }
    }



}


@Preview
@Composable
fun PreviewChat(){
    EventWindowComponent( false, listPhotos = listOf(
        PhotoItem("Hi there", "true", "true"),
        PhotoItem("ohh hello", "false", "true"),
        PhotoItem("Sorry wrong number ", "true", "true"),

        ) , {},{}
    )
}