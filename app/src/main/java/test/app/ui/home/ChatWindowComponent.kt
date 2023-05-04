package test.app.ui.home

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
import test.app.ui.components.ChatListComponent
import test.app.ui.components.MessageInputComponent
import test.app.ui.components.SwitchUserComponent


@Composable
fun ChatWindowComponent(
    messages: List<ChatItem>,
    onMessageSend: (String) -> Unit,
    onUserChanged: (Boolean) -> Unit
) {

    val chatListState = rememberLazyListState()

        Column {

            SwitchUserComponent(
                modifier = Modifier
                    .fillMaxWidth(),
                onCheckedChange = onUserChanged)

            ChatListComponent(
                modifier = Modifier
                    .weight(1.0f)
                    .fillMaxHeight()
                    .background(Color.White),
                chatListItems = messages,
                listState = chatListState,
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


    LaunchedEffect(key1 = messages.size) {
        chatListState.animateScrollToItem(0)
    }

}


@Preview
@Composable
fun PreviewChat(){
    ChatWindowComponent( messages = listOf(
        MessageItem("Hi there", true, true),
        MessageItem("ohh hello", false, true),
        MessageItem("Sorry wrong number ", true, true),

        ) , {},{})
}