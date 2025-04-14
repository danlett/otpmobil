package hu.danlett.otpmobil.ui.state

import hu.danlett.otpmobil.domain.picture.model.Photo

sealed interface PhotoListState
object Loading : PhotoListState
data class Ready(val photos: List<Photo>) : PhotoListState
object Empty : PhotoListState