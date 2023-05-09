package test.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import test.app.ui.theme.ColorChat1
import test.app.ui.theme.ColorChat2
import test.app.ui.theme.ForestFrost


@Composable
fun SwitchUserComponent(
    modifier: Modifier,
    onCheckedChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit

) {
    var isChecked by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    Card(elevation = 20.dp, backgroundColor = ForestFrost) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.Bottom) {
                OutlinedTextField(
                    modifier = Modifier.weight(0.75f).padding(10.dp),
                    colors = TextFieldDefaults.textFieldColors(textColor = Color.Black),
                    value = searchText,
                    onValueChange = { searchText = it })

                SearchButton(modifier = Modifier.padding(bottom = 10.dp),text = "Search") { onSearch(searchText) }
            }

            Row(modifier = Modifier.weight(0.6f)) {
                Switch(
                    colors = SwitchDefaults.colors(
                        uncheckedThumbColor = ColorChat1,
                        uncheckedTrackColor = ColorChat1,
                        checkedThumbColor = ColorChat2,
                        checkedTrackColor = ColorChat2
                    ),
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = it
                        onCheckedChange(isChecked)
                    }
                )
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 18.sp,
                    text = "Switch User"
                )
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
    SwitchUserComponent(Modifier.fillMaxWidth(), {}, {})
}