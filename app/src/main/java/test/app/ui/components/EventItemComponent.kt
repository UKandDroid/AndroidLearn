package test.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import  test.app.R

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
                contentDescription = null, // Provide a meaningful description here
                modifier = Modifier.size(100.dp).padding(end = 16.dp))

            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = desc,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewImageWithTextCard() {
    EventItemComponent(
        name = "Disco somewhere or anyw",
        desc = "Artist inclued",
        imageUrl = "https://somewhere.drawable.ic_launcher_foreground"
    )
}
