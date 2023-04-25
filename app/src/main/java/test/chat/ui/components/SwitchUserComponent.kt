package test.chat.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import test.chat.ui.theme.ColorChat1
import test.chat.ui.theme.ColorChat2
import test.chat.ui.theme.ForestFrost


@Composable
fun SwitchUserComponent(modifier: Modifier,
    onCheckedChange: (Boolean) -> Unit
) {
    var isChecked by remember { mutableStateOf(false) }

    Card(elevation = 20.dp, backgroundColor = ForestFrost) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 18.sp,
                text = "Switch User"
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


@Composable
@Preview
fun previewSwitchUserComponent() {
    SwitchUserComponent(Modifier.fillMaxWidth(),{})
}