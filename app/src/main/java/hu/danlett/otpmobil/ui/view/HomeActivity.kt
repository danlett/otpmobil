package hu.danlett.otpmobil.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import hu.danlett.otpmobil.ui.components.HomeScreen
import hu.danlett.otpmobil.ui.theme.OtpMobilAppTheme

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OtpMobilAppTheme {
                HomeScreen()
            }
        }
    }
}