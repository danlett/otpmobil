package hu.danlett.otpmobil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.danlett.otpmobil.domain.picture.gateway.NetworkPictureGateway
import hu.danlett.otpmobil.network.picture.model.NetworkPhotosRequestSuccess
import hu.danlett.otpmobil.ui.state.HomeUiState
import hu.danlett.otpmobil.ui.state.Ready
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkPictureGateway: NetworkPictureGateway
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun onActivityStarted() {
        fetchFirstPicturePage()
    }

    private fun fetchFirstPicturePage() {
        viewModelScope.launch(Dispatchers.IO) {
            val photos = networkPictureGateway.getPhotos("dog", 1)
            Timber.e(photos.toString())
            if (photos is NetworkPhotosRequestSuccess) {
                _uiState.update {
                    it.copy(
                        listState = Ready(photos.photos)
                    )
                }
            }
        }
    }
}