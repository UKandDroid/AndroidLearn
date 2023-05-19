package test.app.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import test.app.domain.model.ui.MessageItem
import test.app.domain.repo.LocalRepository
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val localRepository: LocalRepository,
) : ViewModel() {

    init {
        _uiState.value = EventsLoading()
    }


    lateinit var _uiState: MutableState<UiStates>
    val uiState: State<UiStates> = _uiState


    fun eventSearch(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
        }
    }


}