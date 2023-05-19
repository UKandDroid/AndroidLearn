package test.app.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import test.app.domain.repo.LocalRepository
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val localRepository: LocalRepository,
) : ViewModel() {

    init {

    }

    var _uiState: MutableState<UiStates> = mutableStateOf(EventsLoading())
    val uiState: State<UiStates> = _uiState
    private val _isLoading = mutableStateOf(false)
    val isLoading = _isLoading


    fun refreshEvents() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(2000L)
            _isLoading.value = false
        }
    }


    fun eventSearch(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
        }
    }


}