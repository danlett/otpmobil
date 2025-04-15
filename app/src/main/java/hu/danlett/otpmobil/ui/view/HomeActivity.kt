package hu.danlett.otpmobil.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hu.danlett.otpmobil.ui.components.HomeScreen
import hu.danlett.otpmobil.ui.theme.OtpMobilAppTheme
import hu.danlett.otpmobil.ui.viewmodel.HomeViewModel

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
    }

    override fun onStart() {
        super.onStart()
        viewModel.onActivityStarted()
    }
}