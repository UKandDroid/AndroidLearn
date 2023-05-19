package test.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import test.app.ui.theme.ForestFrost


@Composable
fun SearchComponent(
    modifier: Modifier,
    onSearch: (String) -> Unit

) {
    var searchText by remember { mutableStateOf("") }

    Card(elevation = 20.dp, backgroundColor = ForestFrost) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.Bottom) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = {Text("Search  ")},
                    modifier = Modifier.weight(0.75f).padding(10.dp),
                    colors = TextFieldDefaults.textFieldColors(textColor = Color.Black, placeholderColor = Color.Gray))

                SearchButton(modifier = Modifier.padding(bottom = 10.dp),text = "Search") { onSearch(searchText) }
            }
        }
    }
}


@Composable
fun SearchButton(modifier: Modifier, text: String, onClick: () -> Unit) {
    Button(modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
    )

    {
        Text( text = text, color = Color.White)
    }
}

@Composable
@Preview
fun previewSwitchUserComponent() {
    SearchComponent(Modifier.fillMaxWidth(), {})
}