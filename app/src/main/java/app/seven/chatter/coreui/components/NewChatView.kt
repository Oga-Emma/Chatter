package app.seven.chatter.coreui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.SupervisorAccount
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.seven.chatter.coreui.theme.ChatterTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewChatViewBottomSheet() {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { showBottomSheet = true }
        ) {
            Text("Display partial bottom sheet")
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(),
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false }
            ) {
                Text(
                    "Swipe up to open sheet. Swipe down to dismiss.",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun NewChatView(modifier: Modifier = Modifier, onCancel: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(8)
        ) {
            Column {
                ActionItem(
                    title = "New Chat",
                    subtitle = "Send a message to your contact",
                    icon = Icons.AutoMirrored.Outlined.Message,
                    onClick = {}
                )
                HorizontalDivider(color = Color.LightGray, thickness = 0.3.dp)
                ActionItem(
                    title = "New Contact",
                    subtitle = "Add a contact to be able to send messages",
                    icon = Icons.Outlined.Contacts,
                    onClick = {}
                )
                HorizontalDivider(color = Color.LightGray, thickness = 0.3.dp)
                ActionItem(
                    title = "New Community",
                    subtitle = "Join the community around you",
                    icon = Icons.Outlined.SupervisorAccount,
                    onClick = {}
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        FilledTonalButton(
            onClick = {
                onCancel()
            },
            colors = ButtonDefaults.filledTonalButtonColors()
                .copy(containerColor = Color.White)
        ) {
            Spacer(modifier = Modifier.width(36.dp))
            Text(text = "Cancel", color = Color.Black)
            Spacer(modifier = Modifier.width(36.dp))
        }
    }
}

@Composable
private fun ActionItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Icon(icon, title)
        Spacer(modifier = Modifier.width(24.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            Text(text = subtitle, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Preview
@Composable
private fun NewChatViewPreview() {
    ChatterTheme {
        NewChatView(onCancel = {})
    }
}
