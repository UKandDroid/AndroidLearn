package test.app.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import test.app.domain.model.PhotoItem
import test.app.domain.model.InfoItem
import test.app.domain.model.ScreenListItem
import test.app.domain.repo.LocalRepository
import test.app.domain.util.StringRes
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val strRes: StringRes
) : ViewModel() {

    private var _uiState: MutableState<UiStates> = mutableStateOf(UiStates.PhotosUpdate(strRes.LOADING))
    private val _isLoading = mutableStateOf(false)
    val uiState: State<UiStates> = _uiState
    val isLoading: State<Boolean> = _isLoading

    init {
        refreshPhotos()
    }

    fun refreshPhotos() {
        viewModelScope.launch {
            _isLoading.value = true
            val success = localRepository.refreshPhotos()

            launch {
                delay(500)                         // keep spinner for half a second, so it does not look like a glitch
                _isLoading.value = false
            }

            val listPhotos: List<ScreenListItem> =  localRepository.getAllPhotos().map { PhotoItem(it.title, it.desc, it.url) }
            _uiState.value = UiStates.ListPhotos(if(success) listPhotos else listPhotos + InfoItem(strRes.FAILED_TO_REFRESH))

        }
    }

    fun photoSearch(search: String) {
        viewModelScope.launch {
            val searchResult = localRepository.getPhotoByTitle(search)

            _uiState.value = if(searchResult.isNotEmpty()) {
                UiStates.ListPhotos(searchResult.map { PhotoItem(it.title, it.desc, it.url) })
            } else {
                UiStates.PhotosUpdate(strRes.NO_MATCHING_ITEM)
            }
        }
    }


}