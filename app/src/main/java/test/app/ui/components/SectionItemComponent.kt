package test.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import test.app.ui.theme.ForestFrost

@Composable
fun SectionItemComponent(title: String) {
    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier = Modifier
            .background(ForestFrost, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp),
            color = Color.Gray,
            text = title, style = MaterialTheme.typography.body2
        )
    }
}