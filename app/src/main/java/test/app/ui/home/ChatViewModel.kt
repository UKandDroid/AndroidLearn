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
import test.app.data.StringRes
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
    private val searchChat: ChatSearcher,
    private val strRes: StringRes
) : ViewModel() {

    private var _user = DEFAULT_USER

    private val searchFlow: MutableSharedFlow<List<ChatItem>> = MutableSharedFlow()

    val uiState: StateFlow<ChatUiState> = merge(searchFlow, chatConvertor.convertChat())
        .map { items ->
            ChatUiState(chatItems = items, scrollTo = getItemPositionInList(items))
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            ChatUiState(0, listOf(SectionItem(strRes.LOADING)))
        )

    fun sendMessage(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.saveMessage(text, _user.main)
        }
    }

    fun userChanged(toggleOn: Boolean) {
        _user = if (toggleOn) REMOTE_USER else DEFAULT_USER
    }

    fun userSearch(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchFlow.emit(searchChat.search(uiState.value.chatItems, text).first())
        }
    }

    private fun getItemPositionInList(items: List<ChatItem>): Int {
        val found = items.indexOfFirst { it is MessageItem && it.highlight }
        val indexFound = if (found == -1) 0 else (items.size - found - 2)  // Push items up a bit so they are easy to view
        return if (indexFound < 0) 0 else indexFound
    }
}