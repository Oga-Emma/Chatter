package app.seven.chatter.features.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(): ViewModel() {
    private val _events = Channel<AuthEvent>()
    val events = _events.receiveAsFlow()

    init {
//        authenticate()
    }

    private fun authenticate() {
//        viewModelScope.launch {
//            delay(1000)
////            _events.send(MainEvent.Authenticated)
//            _events.send(AuthEvent.VerifyPhone)
//        }
    }

    fun verifyOtp() = viewModelScope.launch {
//        _events.send(AuthEvent.CompleteProfile)
        _events.send(AuthEvent.Home)
    }

    fun requestOtp()  = viewModelScope.launch {
        _events.send(AuthEvent.VerifyPhone)
    }

    fun saveProfile()  = viewModelScope.launch {
        _events.send(AuthEvent.Home)
    }

}

sealed interface AuthEvent {
    data object VerifyPhone: AuthEvent
    data object CompleteProfile: AuthEvent
    data object Home: AuthEvent
}
