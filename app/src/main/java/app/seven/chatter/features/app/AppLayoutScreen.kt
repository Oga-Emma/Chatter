package app.seven.chatter.features.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.seven.chatter.R
import app.seven.chatter.features.app.home.HomeScreen
import app.seven.chatter.ui.theme.ChatterTheme


sealed class Routes(val name: String, val route: String, val icon: ImageVector) {
    data object Home : Routes("Home", "home", Icons.Outlined.Home)
    data object Status : Routes("Status", "status", Icons.Outlined.Person)
    data object Account : Routes("Account", "account", Icons.Outlined.Person)
}

val topLevelRoutes = listOf(
    Routes.Home,
    Routes.Status,
    Routes.Account,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppLayoutScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Chatter", fontWeight = FontWeight.SemiBold)
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = "Search")
                    }
                }
            )
        },
        bottomBar = {
            Box(
            ) {
                Column {
                    HorizontalDivider(
                        color = Color.LightGray.copy(alpha = 0.5f)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 16.dp, top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        BottomBarItem(
                            resource = R.drawable.ic_home,
                            onClick = {},
                            description = "",
                        )
                        FilledTonalButton(
                            onClick = {},
                            colors = ButtonDefaults.filledTonalButtonColors()
                                .copy(containerColor = Color.Black)
                        ) {
                            Icon(imageVector = Icons.Rounded.Add, "add chat")
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("New Chat")
                        }
                        BottomBarItem(
                            resource = R.drawable.ic_profile,
                            onClick = {},
                            description = "",
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Routes.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(route = Routes.Home.route) {
                HomeScreen()
            }
            composable(route = Routes.Status.route) {
                HomeScreen()
            }
            composable(route = Routes.Account.route) {
                Box {

                }
            }
        }
    }
}

@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    description: String,
    resource: Int,
    onClick: () -> Unit
) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(painter = painterResource(id = resource), contentDescription = description)
    }
}

@Preview
@Composable
private fun AppLayoutScreenPreview() {
    ChatterTheme {
        AppLayoutScreen()
    }
}
