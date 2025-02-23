package app.seven.chatter.features.home.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PersonRemove
import androidx.compose.material.icons.outlined.PhoneIphone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.seven.chatter.coreui.theme.ChatterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Account",
                        fontWeight = FontWeight.SemiBold
                    )
                },
                actions = {}
            )
        },
        bottomBar = {

        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("PROFILE", fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column {
                    AccountAction(
                        label = "Edit Profile",
                        leadingIcon = Icons.Outlined.AccountCircle
                    )
                    HorizontalDivider(color = Color.LightGray, thickness = 0.2.dp)
                    AccountAction(
                        label = "Change Phone Number",
                        leadingIcon = Icons.Outlined.PhoneIphone
                    )
                    HorizontalDivider(color = Color.LightGray, thickness = 0.2.dp)
                    AccountAction(
                        label = "Delete Account",
                        leadingIcon = Icons.Outlined.PersonRemove
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text("SUPPORT", fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column {
                    AccountAction(
                        label = "FAQ",
                        leadingIcon = Icons.AutoMirrored.Default.HelpOutline
                    )
                    HorizontalDivider(color = Color.LightGray, thickness = 0.2.dp)
                    AccountAction(
                        label = "User Guide",
                        leadingIcon = Icons.Outlined.Info
                    )
                    HorizontalDivider(color = Color.LightGray, thickness = 0.2.dp)
                    AccountAction(
                        label = "About Chatter",
                        leadingIcon = Icons.Outlined.Description
                    )
                    HorizontalDivider(color = Color.LightGray, thickness = 0.2.dp)
                    AccountAction(
                        label = "Contact US",
                        leadingIcon = Icons.Outlined.Call
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                AccountAction(label = "Logout", leadingIcon = Icons.AutoMirrored.Rounded.Logout)
            }
        }
    }
}

@Composable
fun AccountAction(modifier: Modifier = Modifier, label: String, leadingIcon: ImageVector) {
    Row(
        modifier = modifier.padding(16.dp)
    ) {
        Icon(leadingIcon, "")
        Spacer(modifier = Modifier.width(16.dp))
        Text(modifier = Modifier.weight(1f), text = label)
        Spacer(modifier = Modifier.width(16.dp))
        Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, "")
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ChatterTheme {
        AccountScreen()
    }
}