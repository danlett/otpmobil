package hu.danlett.otpmobil.ui.state

import hu.danlett.otpmobil.domain.picture.model.Photo

sealed interface PhotoListState
object Uninitialized : PhotoListState
data class Initialized(
	val photos: List<Photo>,
	val page: Int,
	val hasMore: Boolean,
	val query: String
) : PhotoListState
object Empty : PhotoListState