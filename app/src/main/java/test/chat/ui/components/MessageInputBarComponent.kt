package test.chat.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MessageInputComponent(
    modifier: Modifier = Modifier,
    onSendPressed: (String) -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .height(IntrinsicSize.Max)
            .fillMaxWidth()
    ) {
        var messageText by remember { mutableStateOf("") }

        OutlinedTextField(
            value = messageText,
            onValueChange = { messageText = it },
            placeholder = { Text("Type your message here") },
            modifier = Modifier
                .weight(.9f)
                .background(color = MaterialTheme.colors.surface)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Box(modifier = Modifier
            .align(Alignment.CenterVertically)
            .clickable {
                onSendPressed(messageText)
                messageText = ""
            }
        ) {
            Icon(
                Icons.Filled.Send,
                contentDescription = "Send message"
            )
        }
    }
}

@Preview
@Composable
fun MessageBarPreview() {
    MessageInputComponent(onSendPressed = {})
}