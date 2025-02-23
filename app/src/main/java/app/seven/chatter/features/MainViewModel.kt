package app.seven.chatter.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val _events = Channel<MainEvent>()
    val events = _events.receiveAsFlow()

    init {
        authenticate()
    }

    private fun authenticate() {
        viewModelScope.launch {
            delay(500)
//            _events.send(MainEvent.Authenticated)
            _events.send(MainEvent.UnAuthenticated)
        }
    }

}

sealed interface MainEvent {
    data object Authenticated: MainEvent
    data object UnAuthenticated: MainEvent
}
