package test.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import test.app.ui.components.ChatWindowComponent
import test.app.ui.theme.ChatTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ChatTheme {

                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                Surface(
                    color = MaterialTheme.colors.background,
                ) {
                    ChatWindowComponent(
                        scrollPos = uiState.scrollTo,
                        messages = uiState.chatItems,
                        onMessageSend = {
                            viewModel.sendMessage(it)
                        },
                        onUserChanged = {
                            viewModel.userChanged(it)
                        },
                        onSearchClick = {
                            viewModel.userSearch(it)
                        }
                        )
                }
            }
        }
    }
}