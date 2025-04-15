package hu.danlett.otpmobil.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import hu.danlett.otpmobil.domain.picture.model.Photo
import hu.danlett.otpmobil.ui.state.PhotoListState
import hu.danlett.otpmobil.ui.state.Ready

@Composable
fun PhotosList(
	photosListState: PhotoListState
) {
	when (photosListState) {
		is Ready -> PhotosGrid(photosListState.photos)
		else -> Unit
	}
}

@Composable
fun PhotosGrid(
	photos: List<Photo>
) {
	LazyVerticalStaggeredGrid(
		columns = StaggeredGridCells.Fixed(2),
		verticalItemSpacing = 4.dp,
		horizontalArrangement = Arrangement.spacedBy(4.dp),
	) {
		items(photos.size) { index ->
			PhotoThumbnail(photos[index])
		}
	}
}