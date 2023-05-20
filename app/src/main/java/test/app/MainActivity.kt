package test.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import test.app.ui.home.EventViewModel
import test.app.ui.components.EventWindowComponent
import test.app.ui.theme.ChatTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ChatTheme {
                val uiState by viewModel.uiState

                Surface(color = MaterialTheme.colors.background) {
                    EventWindowComponent(
                        events = uiState.items,
                        onSearchClick = {
                            viewModel.eventSearch(it)
                        }, viewModel = viewModel)
                }
            }
        }
    }
}