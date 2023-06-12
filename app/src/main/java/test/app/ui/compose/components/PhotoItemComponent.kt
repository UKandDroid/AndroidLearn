package test.app.ui.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun EventItemComponent(
    name: String,
    desc: String,
    imageUrl: String
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = 8.dp) {
        Row(
            modifier = Modifier.padding(8.dp)) {
            Image(
                painter =  rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier.size(100.dp).padding(end = 16.dp))

            Column {
                Text(
                    text = name,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = desc,
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewImageWithTextCard() {
    EventItemComponent(
        name = "Disco somewhere or anywhere",
        desc = "Artist included",
        imageUrl = "https://somewhere.drawable.ic_launcher_foreground"
    )
}
