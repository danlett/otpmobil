package hu.danlett.otpmobil.ui.state

data class HomeUiState(
    val isLoading: Boolean = true,
    val listState: PhotoListState = Loading,
    val navigationState: HomeNavigationState = Idle
)