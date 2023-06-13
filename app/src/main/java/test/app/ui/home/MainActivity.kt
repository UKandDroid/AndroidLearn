package test.app.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import test.app.ui.compose.components.MainWindowComponent
import test.app.ui.compose.theme.TestAppTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestAppTheme {
                val uiState by viewModel.uiState
                val isLoading by viewModel.isLoading

                Surface(color = MaterialTheme.colors.background) {
                    MainWindowComponent(
                        isLoading,
                        listPhotos = uiState.items,
                        onSearchClick = {
                            viewModel.photoSearch(it)
                        },
                        onRefresh = {
                            viewModel.refreshPhotos()
                        } )
                }
            }
        }
    }
}