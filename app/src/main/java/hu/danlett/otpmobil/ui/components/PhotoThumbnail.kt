package hu.danlett.otpmobil.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import hu.danlett.otpmobil.R
import hu.danlett.otpmobil.domain.picture.model.Photo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoThumbnail(
	photo: Photo,
	onPhotoClicked: (Photo) -> Unit = {},
) {
	AsyncImage(
		modifier = Modifier
			.aspectRatio(1f)
			.clip(RoundedCornerShape(12.dp))
			.clickable(
				onClick = { onPhotoClicked(photo) }
			),
		model = photo.imageUrl,
		contentDescription = stringResource(id = R.string.image_thumbnail_content_description),
		error = painterResource(
			id = R.drawable.image_24px,
		),
		contentScale = ContentScale.Crop,
	)
}