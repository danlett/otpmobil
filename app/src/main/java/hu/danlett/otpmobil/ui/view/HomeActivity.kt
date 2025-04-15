package hu.danlett.otpmobil.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import hu.danlett.otpmobil.domain.picture.model.Photo
import hu.danlett.otpmobil.ui.components.HomeScreen
import hu.danlett.otpmobil.ui.state.NavigateToDetail
import hu.danlett.otpmobil.ui.theme.OtpMobilAppTheme
import hu.danlett.otpmobil.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OtpMobilAppTheme {
                HomeScreen(viewModel)
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (val navigationState = it.navigationState) {
                        is NavigateToDetail -> navigateToDetailScreen(navigationState.photo)
                        else -> {}
                    }
                    viewModel.onNavigationStarted()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onActivityStarted()
    }

    private fun navigateToDetailScreen(photo: Photo) {
        startActivity(DetailsActivity.newInstance(this, photo))
    }
}