package test.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import test.app.ui.theme.ColorChat1
import test.app.ui.theme.ColorChat2


@Composable
fun MessageWithBg(
    text: String,
    isUser: Boolean,
    modifier: Modifier = Modifier,
    hasTail: Boolean = false,
    highlight: Boolean = false
) {
    val bubbleColor = if (isUser) ColorChat1 else ColorChat2

    val shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, if (hasTail) 0.dp else 16.dp)
    val style = if(highlight) MaterialTheme.typography.h4  else MaterialTheme.typography.body1

    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        horizontalArrangement = if (isUser) Arrangement.Start else Arrangement.End) {
        Row(
            modifier = modifier
                .background(bubbleColor, shape)
                .padding(10.dp)
        ) {
            Text(
                text = text,
                color = Color.Black,
                style = style,
            )
        }
    }
}

@Preview
@Composable
fun conversationPreview() {
    Column {
        MessageWithBg(text = "Hello!", isUser = false, hasTail = true)
        MessageWithBg(text = "Hello there!", isUser = true)
    }
}