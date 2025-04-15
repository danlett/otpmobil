package hu.danlett.otpmobil.ui.state

data class HomeUiState(
    val isLoading: Boolean = true,
    val listState: PhotoListState = Uninitialized,
    val navigationState: HomeNavigationState = Idle
)