package test.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import test.app.domain.model.ui.SectionItem
import test.app.domain.repo.LocalRepository
import test.app.domain.util.ChatConvertor
import test.app.domain.util.DEFAULT_USER
import test.app.domain.util.REMOTE_USER
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val chatConvertor: ChatConvertor
) : ViewModel() {

    private var _user = DEFAULT_USER

    val uiState: StateFlow<ChatUiState> = chatConvertor.convertChat()
        .map { items -> ChatUiState(chatItems = items)
        }.stateIn(viewModelScope, SharingStarted.Eagerly, ChatUiState(listOf(SectionItem("Loading ..."))))

    fun sendMessage(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.saveMessage(text, _user.main)
        }
    }

    fun userChanged(toggleOn: Boolean) {
        _user = if (toggleOn) REMOTE_USER else DEFAULT_USER
    }

}