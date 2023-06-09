package test.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import test.app.ui.theme.ColorChat1
import test.app.ui.theme.ColorChat2
import test.app.ui.theme.ForestFrost
import test.app.ui.theme.ForestFrostOutline


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
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Row(modifier = Modifier.weight(1f).fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
                BasicTextField(searchText,
                    onValueChange = { searchText = it; onSearch(it)},
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    ),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier
                                .padding(8.dp) // margin left and right
                                .fillMaxWidth()
                                .background(color = Color(Color.Transparent.value), shape = RoundedCornerShape(size = 16.dp))
                                .border(
                                    width = 2.dp,
                                    color = ForestFrostOutline,
                                    shape = RoundedCornerShape(size = 16.dp)
                                )
                                .padding(all = 8.dp), // inner padding
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search icon",
                                tint = Color.DarkGray
                            )
                            Spacer(modifier = Modifier.width(width = 8.dp))
                            innerTextField()
                        }
                    }
                )

            }

            Row(modifier = Modifier.weight(0.8f)) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .align(CenterVertically),
                    color = Color.Black,
                    fontSize = 18.sp,
                    text = "Switch User",


                )
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

            }

        }
    }
}


@Composable
fun SearchButton(modifier: Modifier, text: String, onClick: () -> Unit) {
    Button(modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
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