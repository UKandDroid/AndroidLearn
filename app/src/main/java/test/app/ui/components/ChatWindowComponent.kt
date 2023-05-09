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
import androidx.compose.ui.unit.dp
import test.app.domain.model.ui.ChatItem
import test.app.domain.model.ui.MessageItem


@SuppressLint("SuspiciousIndentation")
@Composable
fun ChatWindowComponent(
    scrollPos : Int,
    messages: List<ChatItem>,
    onMessageSend: (String) -> Unit,
    onUserChanged: (Boolean) -> Unit,
    onSearchClick: (String) -> Unit
) {

    val chatListState = rememberLazyListState()

    val scrollPosition = remember { derivedStateOf { if(scrollPos ==-1) 0 else scrollPos  } }

        Column {

            SwitchUserComponent(
                modifier = Modifier.fillMaxWidth(),
                onCheckedChange = onUserChanged,
                onSearch = onSearchClick
                )

            ChatListComponent(
                modifier = Modifier
                    .weight(1.0f)
                    .fillMaxHeight()
                    .background(Color.White),
                chatListItems = messages,
                listState = chatListState
            )
            MessageInputComponent(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.surface
                    )
                    .padding(10.dp)
                    .fillMaxWidth(),
                onSendPressed = {
                    onMessageSend(it)
                }
            )

    }


    LaunchedEffect(key1 = scrollPosition.value) {
        chatListState.animateScrollToItem(scrollPosition.value)
    }

}


@Preview
@Composable
fun PreviewChat(){
    ChatWindowComponent( 0,messages = listOf(
        MessageItem("Hi there", true, true),
        MessageItem("ohh hello", false, true),
        MessageItem("Sorry wrong number ", true, true),

        ) , {},{ },{})
}