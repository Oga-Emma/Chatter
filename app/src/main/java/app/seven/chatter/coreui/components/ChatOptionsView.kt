package app.seven.chatter.coreui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.seven.chatter.R
import app.seven.chatter.core.models.ChatMessage
import app.seven.chatter.coreui.theme.ChatterTheme
import java.time.OffsetDateTime
import java.util.UUID

@Composable
fun ChatOptionsView(modifier: Modifier = Modifier, onCancel: () -> Unit) {
    val message = ChatMessage(
        id = UUID.randomUUID(),
        sender = ChatMessage.MessageSender(
            fullName = "John Doe"
        ),
        message = "Hi, Asal",
        sentAt = OffsetDateTime.now(),
        receivedAt = OffsetDateTime.now(),
        isMine = true,
        isDelivered = true,
        isRead = true,
    )
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8)
    ) {
        Column {
            Row(modifier = modifier.padding(vertical = 4.dp).fillMaxWidth(),) {

            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(color = Color.LightGray.copy(0.3f), shape = RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(text = message.message, fontSize = 14.sp)
            }
            ActionItem(
                title = "Copy",
                icon = { Icon(Icons.Outlined.ContentCopy, "") },
                onClick = {}
            )
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 0.2.dp
            )
            ActionItem(
                title = "Reply",
                icon = { Icon(painter = painterResource(R.drawable.ic_backward), "") },
                onClick = {}
            )
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 0.2.dp
            )
            ActionItem(
                title = "Forward",
                icon = { Icon(painter = painterResource(R.drawable.ic_forward), "") },
                onClick = {}
            )
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 0.2.dp
            )
            ActionItem(
                title = "Delete",
                icon = { Icon(painter = painterResource(R.drawable.ic_delete), "") },
                onClick = {}
            )
        }
    }
}


@Composable
private fun ActionItem(
    modifier: Modifier = Modifier,
    title: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f), text = title
        )
        icon()
    }
}

@Preview
@Composable
private fun ChatOptionsViewPreview() {
    ChatterTheme {
        ChatOptionsView(onCancel = {})
    }
}
