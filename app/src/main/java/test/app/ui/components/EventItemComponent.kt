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
import  test.app.R

@Composable
fun EventItemComponent(
    headerText: String,
    descriptionText: String,
    imageResId: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = 8.dp) {
        Row(
            modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(imageResId),
                contentDescription = null, // Provide a meaningful description here
                modifier = Modifier.size(100.dp).padding(end = 16.dp))

            Column {
                Text(
                    text = headerText,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = descriptionText,
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
        headerText = "Disco somewhere or anyw",
        descriptionText = "Artist inclued",
        imageResId = R.drawable.ic_launcher_foreground
    )
}
