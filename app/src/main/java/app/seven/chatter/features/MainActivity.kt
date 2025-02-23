package app.seven.chatter.features

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import app.seven.chatter.core.utils.ObserveAsEvents
import app.seven.chatter.features.auth.AuthActivity
import app.seven.chatter.features.home.HomeActivity
import app.seven.chatter.coreui.theme.ChatterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatterTheme {
                val mainViewModel: MainViewModel = hiltViewModel()
                val context = LocalContext.current

                ObserveAsEvents(events = mainViewModel.events) { event ->
                    when(event) {
                        MainEvent.Authenticated -> {
                            startActivity(Intent(context, HomeActivity::class.java))
                        }
                        MainEvent.UnAuthenticated -> {
                            startActivity(Intent(context, AuthActivity::class.java))
                        }
                    }
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
