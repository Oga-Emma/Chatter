package app.seven.chatter.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface HomeViewModel {
    fun onAction(action: AppAction)
}

open class TestAppViewModelImpl : HomeViewModel {
    override fun onAction(action: AppAction) {

    }
}

@HiltViewModel
class HomeViewModelImpl @Inject constructor() : HomeViewModel, ViewModel() {

//    private val _state = MutableStateFlow(CoinListState())
//    val state = _state
//        .onStart { loadCoins() }
//        .stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(5000L),
//            CoinListState()
//        )

    private val _events = Channel<AppEvent>()
    val events = _events.receiveAsFlow()

    override fun onAction(action: AppAction) {
        when (action) {
            is AppAction.OpenNewChat -> {
                viewModelScope.launch {
                    _events.send(AppEvent.OpenNewChatBottomSheet)
                }
            }

            AppAction.CloseNewChat -> {
                viewModelScope.launch {
                    _events.send(AppEvent.CloseNewChatBottomSheet)
                }
            }

            AppAction.OpenConversation -> {
                viewModelScope.launch {
                    _events.send(AppEvent.OpenConversationScreen)
                }
            }

            AppAction.AccountScreen ->
                viewModelScope.launch {
                    _events.send(AppEvent.ShowAccountScreen)
                }
            AppAction.HomeScreen ->
                viewModelScope.launch {
                    _events.send(AppEvent.ShowHomeScreen)
                }

            AppAction.NavigateBack ->
                viewModelScope.launch {
                    _events.send(AppEvent.NavigateBack)
                }
        }
    }
}

sealed interface AppAction {
    data object HomeScreen: AppAction
    data object AccountScreen: AppAction
    data object OpenNewChat: AppAction
    data object CloseNewChat: AppAction
    data object OpenConversation: AppAction
    data object NavigateBack: AppAction
}

sealed interface AppEvent {
    data object ShowHomeScreen: AppEvent
    data object ShowAccountScreen: AppEvent
    data object OpenNewChatBottomSheet: AppEvent
    data object CloseNewChatBottomSheet: AppEvent
    data object OpenConversationScreen: AppEvent
    data object NavigateBack: AppEvent
}
