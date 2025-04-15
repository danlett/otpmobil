package hu.danlett.otpmobil.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import hu.danlett.otpmobil.domain.picture.model.Photo
import hu.danlett.otpmobil.ui.state.Initialized
import hu.danlett.otpmobil.ui.state.PhotoListState

@Composable
fun PhotosList(
	photosListState: PhotoListState,
	onLoadMore: () -> Unit
) {
	when (photosListState) {
		is Initialized -> PhotosGrid(photosListState.photos, onLoadMore)
		else -> Unit
	}
}

@Composable
fun PhotosGrid(
	photos: List<Photo>,
	onLoadMore: () -> Unit,
) {
	val listState = rememberLazyGridState()

	val isScrollToEnd by remember {
		derivedStateOf {
			listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1
		}
	}

	if (isScrollToEnd) {
		onLoadMore()
	}

	LazyVerticalGrid(
		columns = GridCells.Fixed(2),
		verticalArrangement = Arrangement.spacedBy(4.dp),
		horizontalArrangement = Arrangement.spacedBy(4.dp),
		state = listState
	) {
		items(photos.size) { index ->
			PhotoThumbnail(photos[index])
		}
	}
}