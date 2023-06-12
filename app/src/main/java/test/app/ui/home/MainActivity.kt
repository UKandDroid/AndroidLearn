package test.app.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import test.app.ui.compose.components.EventWindowComponent
import test.app.ui.home.EventViewModel
import test.app.ui.compose.theme.TestAppTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestAppTheme {
                val uiState by viewModel.uiState
                val isLoading by viewModel.isLoading

                Surface(color = MaterialTheme.colors.background) {
                    EventWindowComponent(
                        isLoading,
                        events = uiState.items,
                        onSearchClick = {
                            viewModel.eventSearch(it)
                        },
                        onRefresh = {
                            viewModel.refreshEvents()
                        } )
                }
            }
        }
    }
}