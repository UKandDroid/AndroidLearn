package test.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import test.app.domain.model.ui.ChatItem
import test.app.domain.model.ui.MessageItem
import test.app.domain.model.ui.SectionItem
import test.app.domain.repo.LocalRepository
import test.app.domain.util.ChatConvertor
import test.app.domain.util.ChatSearcher
import test.app.domain.util.DEFAULT_USER
import test.app.domain.util.REMOTE_USER
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val chatConvertor: ChatConvertor,
    private val searchChat: ChatSearcher
) : ViewModel() {

    private var _user = DEFAULT_USER

    private val searchFlow: MutableStateFlow<List<ChatItem>> = MutableStateFlow(emptyList())

    val uiState: StateFlow<ChatUiState> = merge( searchFlow,chatConvertor.convertChat())
        .onEach {list->
            println(list.indexOfFirst { it is MessageItem && it.highlight })
        }
        .map { items -> ChatUiState(chatItems = items, scrollTo = items.indexOfFirst { it is MessageItem && it.highlight })
        }.stateIn(viewModelScope, SharingStarted.Eagerly, ChatUiState(0, listOf(SectionItem("Loading ..."))))


    fun sendMessage(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.saveMessage(text, _user.main)
        }
    }

    fun userChanged(toggleOn: Boolean) {
        _user = if (toggleOn) REMOTE_USER else DEFAULT_USER
    }

    fun userSearch(text: String){
        viewModelScope.launch(Dispatchers.IO) {
            searchFlow.value = searchChat.search(uiState.value.chatItems, text).first()
        }
    }
}