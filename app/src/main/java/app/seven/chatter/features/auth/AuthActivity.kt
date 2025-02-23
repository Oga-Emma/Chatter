package app.seven.chatter.features.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.seven.chatter.core.utils.ObserveAsEvents
import app.seven.chatter.features.auth.completeprofile.CompleteProfileScreen
import app.seven.chatter.features.auth.phonenumber.PhoneNumberScreen
import app.seven.chatter.features.auth.verifyphone.VerifyPhoneScreen
import app.seven.chatter.features.home.HomeActivity
import app.seven.chatter.coreui.theme.ChatterTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable


//Routes
@Serializable object EnterPhoneNumber
@Serializable object VerifyPhoneNumber
@Serializable object CompleteProfile

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatterTheme {
                val authViewModel: AuthViewModel = hiltViewModel()
                val navController = rememberNavController()

                val context = LocalContext.current
                ObserveAsEvents(events = authViewModel.events) { event ->
                    when(event) {
                        AuthEvent.CompleteProfile -> {
                            navController.navigate(CompleteProfile)
                        }
                        AuthEvent.VerifyPhone -> {
                            navController.navigate(VerifyPhoneNumber)
                        }
                        AuthEvent.Home -> {
                            startActivity(Intent(context, HomeActivity::class.java))
                        }
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopAppBar(title = {}) },
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = EnterPhoneNumber,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<EnterPhoneNumber> {
                            PhoneNumberScreen(
                                onContinue = {
                                    authViewModel.requestOtp()
                                }
                            )
                        }
                        composable<VerifyPhoneNumber> {
                            VerifyPhoneScreen(
                                onVerifyPhoneNumber = {
                                    authViewModel.verifyOtp()
                                }
                            )
                        }
                        composable<CompleteProfile> {
                            CompleteProfileScreen(
                                onSaveChanges = {
                                    authViewModel.saveProfile()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
