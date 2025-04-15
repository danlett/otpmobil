package hu.danlett.otpmobil.ui.state

data class HomeUiState(
    val listState: PhotoListState = Uninitialized,
    val navigationState: HomeNavigationState = Idle
)