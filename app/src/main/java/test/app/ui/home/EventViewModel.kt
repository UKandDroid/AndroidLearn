package test.app.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import test.app.domain.model.ui.EventItem
import test.app.domain.model.ui.MessageItem
import test.app.domain.model.ui.ScreenListItem
import test.app.domain.repo.LocalRepository
import test.app.domain.util.StringRes
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val strRes: StringRes
) : ViewModel() {

    private var _uiState: MutableState<UiStates> = mutableStateOf(UiStates.EventsUpdate(strRes.LOADING))
    private val _isLoading = mutableStateOf(false)
    val uiState: State<UiStates> = _uiState
    val isLoading: State<Boolean> = _isLoading

    init {
        refreshEvents()
    }

    fun refreshEvents() {
        viewModelScope.launch {
            _isLoading.value = true
            val success = localRepository.refreshEvents()

            launch {
                delay(500)                         // keep spinner for half a second, so it does not look like a glitch
                _isLoading.value = false
            }

            val listEvents: List<ScreenListItem> =  localRepository.getAllEvents().map { EventItem(it.name, it.desc, it.url) }
            _uiState.value = UiStates.ListEvents(if(success) listEvents else listEvents + MessageItem(strRes.FAILED_TO_REFRESH))

        }
    }

    fun eventSearch(search: String) {
        viewModelScope.launch {
            val searchResult = localRepository.getEvents(search)

            _uiState.value = if(searchResult.isNotEmpty()) {
                UiStates.ListEvents(searchResult.map { EventItem(it.name, it.desc, it.url) })
            } else {
                UiStates.EventsUpdate(strRes.NO_MATCHING_ITEM)
            }
        }
    }


}