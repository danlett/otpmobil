package hu.danlett.otpmobil.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
	photo: Photo,
	onBackClick: () -> Unit
) {
	Scaffold(
		modifier = Modifier.fillMaxSize(),
		topBar = {
			TopAppBar(
				title = { Text(text = stringResource(R.string.details_title)) },
				navigationIcon = {
					IconButton(onClick = onBackClick) {
						Icon(
							imageVector = Icons.AutoMirrored.Filled.ArrowBack,
							contentDescription = stringResource(id = R.string.back_content_description),
						)
					}
				})
		}
	) { innerPadding ->
		Column(
			modifier = Modifier
				.padding(innerPadding)
				.fillMaxSize()
				.padding(16.dp)
		) {
			AsyncImage(
				modifier = Modifier
					.aspectRatio(1f)
					.clip(RoundedCornerShape(12.dp)),
				model = photo.imageUrl,
				contentDescription = stringResource(id = R.string.image_thumbnail_content_description),
				error = painterResource(
					id = R.drawable.image_24px,
				),
				contentScale = ContentScale.Crop,
			)

			Card(
				modifier = Modifier
					.padding(top = 16.dp)
					.fillMaxWidth(),
				shape = RoundedCornerShape(12.dp)
			) {
				Column(modifier = Modifier.padding(16.dp)) {
					Text(
						text = stringResource(R.string.details_description),
						style = MaterialTheme.typography.titleMedium
					)

					Text(
						modifier = Modifier.padding(top = 16.dp),
						text = stringResource(R.string.image_title, photo.title)
					)

					Text(text = stringResource(R.string.image_id, photo.id))
				}
			}
		}
	}
}