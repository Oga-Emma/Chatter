package app.seven.chatter.features.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.seven.chatter.R
import app.seven.chatter.core.utils.ObserveAsEvents
import app.seven.chatter.features.home.account.AccountScreen
import app.seven.chatter.features.home.conversation.Conversation
import app.seven.chatter.coreui.components.NewChatView
import app.seven.chatter.coreui.theme.ChatterTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

//Routes
@Serializable
sealed class Route(val route: String) {
    @Serializable
    data object Home : Route(route = "home")

    @Serializable
    data object Account : Route(route = "account")

    @Serializable
    data object Conversation
}

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatterTheme {
                val homeViewModel = hiltViewModel<HomeViewModelImpl>()
                val navController = rememberNavController()
                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route

                var showNewChatBottomSheet by remember { mutableStateOf(false) }
                val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
                val scope = rememberCoroutineScope()

                ObserveAsEvents(events = homeViewModel.events) { event ->
                    when (event) {
                        AppEvent.OpenConversationScreen -> navController.navigate(Route.Conversation)
                        AppEvent.CloseNewChatBottomSheet -> showNewChatBottomSheet = false
                        AppEvent.OpenNewChatBottomSheet -> showNewChatBottomSheet = true
                        AppEvent.ShowAccountScreen -> navController.navigate(route = Route.Account.route)
                        AppEvent.ShowHomeScreen -> navController.navigate(route = Route.Home.route)
                        AppEvent.NavigateBack -> navController.navigateUp()
                    }
                }


                val isHome = currentRoute == Route.Home.route
                val isAccount = currentRoute == Route.Account.route
                if (showNewChatBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            homeViewModel.onAction(AppAction.CloseNewChat)
                        },
                        containerColor = Color.Transparent,
                        sheetState = sheetState
                    ) {
                        NewChatView(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            onCancel = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        homeViewModel.onAction(AppAction.CloseNewChat)
                                    }
                                }
                            }
                        )
                    }
                }

                Column {
                    NavHost(
                        modifier = Modifier.weight(1f),
                        navController = navController,
                        startDestination = Route.Home.route,
                    ) {
                        composable(route = Route.Home.route) {
                            HomeScreen(
                                onViewConversation = {
                                    homeViewModel.onAction(AppAction.OpenConversation)
                                }
                            )
                        }
                        composable(route = Route.Account.route) {
                            AccountScreen()
                        }
                        composable<Route.Conversation> {
                            Conversation(
                                navigateBack = {
                                    homeViewModel.onAction(
                                        AppAction.NavigateBack
                                    )
                                }
                            )
                        }
                    }
                    if (isHome || isAccount) {
                        CustomBottomSheet(
                            isHome = isHome,
                            isAccount = isAccount,
                            onAction = homeViewModel::onAction
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CustomBottomSheet(
    modifier: Modifier = Modifier,
    isHome: Boolean,
    isAccount: Boolean,
    onAction: (AppAction) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp, top = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BottomBarItem(
                isSelected = isHome,
                unselectedResource = R.drawable.ic_home,
                selectedResource = R.drawable.ic_home_filled,
                onClick = {
                    onAction(AppAction.HomeScreen)
                },
                description = "",
            )
            FilledTonalButton(
                onClick = {
                    onAction(AppAction.OpenNewChat)
                },
                colors = ButtonDefaults.filledTonalButtonColors()
                    .copy(containerColor = Color.Black)
            ) {
                Icon(imageVector = Icons.Rounded.Add, "add chat")
                Spacer(modifier = Modifier.width(8.dp))
                Text("New Chat")
            }
            BottomBarItem(
                isSelected = isAccount,
                unselectedResource = R.drawable.ic_profile,
                selectedResource = R.drawable.ic_profile_filled,
                onClick = {
                    onAction(AppAction.AccountScreen)
                },
                description = "",
            )
        }
    }
}


@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    description: String,
    isSelected: Boolean,
    unselectedResource: Int,
    selectedResource: Int,
    onClick: () -> Unit
) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(
            painter = painterResource(id = if (isSelected) selectedResource else unselectedResource),
            contentDescription = description
        )
    }
}

