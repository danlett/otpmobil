package hu.danlett.otpmobil.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import hu.danlett.otpmobil.domain.picture.model.Photo
import hu.danlett.otpmobil.ui.components.DetailsScreen
import hu.danlett.otpmobil.ui.theme.OtpMobilAppTheme

@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		enableEdgeToEdge()
		setContent {
			OtpMobilAppTheme {
				intent?.getParcelableExtra<Photo>(INTENT_KEY_PHOTO)?.let { photo ->
					DetailsScreen(
						photo = photo,
						onBackClick = { finish() }
					)
				} ?: finish()
			}
		}
	}

	companion object {
		private const val INTENT_KEY_PHOTO = "photo"

		fun newInstance(context: Context, photo: Photo): Intent {
			return Intent(context, DetailsActivity::class.java).apply {
				putExtra(INTENT_KEY_PHOTO, photo)
			}
		}
	}
}