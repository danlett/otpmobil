package hu.danlett.otpmobil.ui.state

import hu.danlett.otpmobil.domain.picture.model.Photo

sealed interface HomeNavigationState
object Idle : HomeNavigationState
data class NavigateToDetail(val photo: Photo) : HomeNavigationState