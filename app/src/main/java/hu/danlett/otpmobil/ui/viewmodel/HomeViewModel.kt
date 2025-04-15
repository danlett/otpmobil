package hu.danlett.otpmobil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.danlett.otpmobil.domain.picture.gateway.NetworkPictureGateway
import hu.danlett.otpmobil.domain.picture.model.Photo
import hu.danlett.otpmobil.network.picture.model.NetworkPhotosRequestSuccess
import hu.danlett.otpmobil.ui.state.HomeUiState
import hu.danlett.otpmobil.ui.state.Initialized
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
        reloadList("dog")
    }

    fun onLoadMore() {
        uiState.value.listState.let {
            if (it is Initialized && it.hasMore) {
                loadPage(it.query, it.page + 1)
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        reloadList(query)
    }

    private fun reloadList(query: String) {
        // Flickr API uses 1-based indexing
        loadPage(query, 1)
    }

    private fun loadPage(query: String, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = networkPictureGateway.getPhotos(query, page)
            Timber.e(result.toString())
            if (result is NetworkPhotosRequestSuccess) {
                _uiState.update {
                    it.copy(
                        listState = Initialized(
                            photos = updatePhotoList(result.photos, page),
                            page = result.page,
                            hasMore = result.hasMore,
                            query = query
                        )
                    )
                }
            }
        }
    }

    private fun updatePhotoList(photos: List<Photo>, page: Int): List<Photo> {
        if (page == 1) {
            return photos
        }

        val photoList = (uiState.value.listState as? Initialized)?.photos ?: listOf<Photo>()
        return photoList + photos
    }
}