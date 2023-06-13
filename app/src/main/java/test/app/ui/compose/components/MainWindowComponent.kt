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
import test.app.domain.model.ScreenListItem
import test.app.domain.model.PhotoItem


@SuppressLint("SuspiciousIndentation")
@Composable
fun MainWindowComponent(
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
                PhotoListComponent(
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
fun PreviewWindow(){
    MainWindowComponent( false, listPhotos = listOf(
        PhotoItem("Hi there", "true", "true"),
        PhotoItem("ohh hello", "false", "true"),
        PhotoItem("Sorry wrong number ", "true", "true"),

        ) , {},{}
    )
}