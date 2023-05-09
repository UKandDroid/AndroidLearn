package test.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
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

    private val searchFlow: MutableSharedFlow<List<ChatItem>> = MutableSharedFlow()

    val uiState: StateFlow<ChatUiState> = merge( searchFlow, chatConvertor.convertChat())
        .map { items -> ChatUiState(chatItems = items, scrollTo = getItemPositionInList(items))
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
            searchFlow.emit(searchChat.search(uiState.value.chatItems, text).first())
        }
    }

   private fun getItemPositionInList(items: List<ChatItem>) : Int{
        val found = items.indexOfFirst { it is MessageItem && it.highlight }
        return if(found != -1 && items.size > 2) (items.size - found - 2) else 0
    }
}