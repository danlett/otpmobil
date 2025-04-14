package hu.danlett.otpmobil.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.danlett.otpmobil.domain.picture.gateway.NetworkPictureGateway
import hu.danlett.otpmobil.ui.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    }
}