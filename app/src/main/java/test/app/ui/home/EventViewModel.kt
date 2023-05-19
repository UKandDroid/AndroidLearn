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
import test.app.domain.model.ui.EventItem
import test.app.domain.repo.LocalRepository
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val localRepository: LocalRepository,
) : ViewModel() {

    private var _uiState: MutableState<UiStates> = mutableStateOf(UiStates.EventsUpdate("Loading..."))
    val uiState: State<UiStates> = _uiState
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
      refreshEvents()
    }

    fun refreshEvents() {
        viewModelScope.launch {
            _isLoading.value = true
            val success = localRepository.refreshEvents()

            launch {
                delay(1000)                         // keep it for a second, so it does not look like a glitch
                _isLoading.value = false
            }

           _uiState.value = if(success) {
               UiStates.ListEvents( localRepository.getAllEvents().map { EventItem(it.name, it.desc, it.url) })
            } else {
               UiStates.EventsUpdate("Failed to refresh, please try again")
            }
        }
    }

    fun eventSearch(search: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val searchResult = localRepository.getEvents(search)

            _uiState.value = if(searchResult.isNotEmpty()) {
                UiStates.ListEvents( searchResult.map { EventItem(it.name, it.desc, it.url) })
            } else {
                UiStates.EventsUpdate("No matching items found")
            }
        }
    }


}