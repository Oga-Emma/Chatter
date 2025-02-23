package app.seven.chatter.features.home.conversation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.seven.chatter.R
import app.seven.chatter.core.models.ChatMessage
import app.seven.chatter.coreui.components.ChatBubble
import app.seven.chatter.coreui.components.ChatOptionsView
import app.seven.chatter.coreui.components.CircleAvatar
import app.seven.chatter.coreui.theme.ChatterTheme
import kotlinx.coroutines.launch
import java.time.OffsetDateTime
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Conversation(modifier: Modifier = Modifier, navigateBack: () -> Unit) {
    var showDialogBottomSheet by remember {  mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navigateBack()
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft, "")
                    }
                },
                title = {
                    ConversationAppbar()
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_video),
                            contentDescription = "Video"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_call),
                            contentDescription = "Call"
                        )
                    }
                }
            )

        },
    ) { paddingValues ->
        if (showDialogBottomSheet) {
            ModalBottomSheet(
                modifier = modifier.padding(paddingValues),
                onDismissRequest = {
                    showDialogBottomSheet = false
                },
                containerColor = Color.Transparent,
                sheetState = sheetState
            ) {
                ChatOptionsView(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onCancel = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showDialogBottomSheet = false
                            }
                        }
                    }
                )
            }
        }

        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            HorizontalDivider(color = Color.LightGray, thickness = 0.3.dp)
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                items(items = messages) { message ->
                    ChatBubble(
                        message = message,
                        quotedMessage = if (message.quotedMessageId != null) {
                            messages.firstOrNull { it.id == message.quotedMessageId }
                        } else {
                            null
                        }
                    )
                }
            }
            HorizontalDivider(
                color = Color.LightGray,
                thickness = 0.3.dp
            )
            EnterMessageArea()
        }
    }
}

@Composable
fun EnterMessageArea(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        modifier = modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}) {
            Icon(Icons.Default.Add, "")
        }
        OutlinedTextField(
            /*colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray.copy(0.2f),
                unfocusedContainerColor = Color.LightGray.copy(0.2f),
            ),*/
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(24.dp, ),
            value = text,
            placeholder = { Text(text = "New Chat") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLines = 5,
            onValueChange = { it ->
                text = it
            }
        )
        if (text.text.isBlank()) {
            IconButton(onClick = {}) {
                Icon(Icons.Outlined.Mic, "")
            }
        } else {
            IconButton(onClick = {}) {
                Icon(Icons.AutoMirrored.Rounded.Send, "")
            }
        }
    }
}

@Composable
fun ConversationAppbar(modifier: Modifier = Modifier) {
    Row(
        modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        CircleAvatar(
            size = 52
        ) { }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Angel Curtis",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = 10.sp,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Online",
                fontSize = 12.sp,
                color = Color.Gray,
                lineHeight = 10.sp,
            )
        }
    }
}


val id = UUID.randomUUID()
val id2 = UUID.randomUUID()
val messages = listOf(
    ChatMessage(
        id = UUID.randomUUID(),
        sender = ChatMessage.MessageSender(
            fullName = "John Doe"
        ),
        message = "Hi Asal",
        sentAt = OffsetDateTime.now(),
        receivedAt = OffsetDateTime.now(),
        isMine = false,
        isDelivered = true,
        isRead = true,
    ),
    ChatMessage(
        id = id,
        sender = ChatMessage.MessageSender(
            fullName = "John Doe"
        ),
        message = "How do you buy \"nice\" stuff?",
        sentAt = OffsetDateTime.now(),
        receivedAt = OffsetDateTime.now(),
        isMine = false,
        isDelivered = true,
        isRead = true,
        quotedMessageId = id2
    ),
    ChatMessage(
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
    ),
    ChatMessage(
        id = id2,
        sender = ChatMessage.MessageSender(
            fullName = "John Doe"
        ),
        message = "I usually buy directly to the shop to reduce the risk of damaged travel, and prevent any damage",
        sentAt = OffsetDateTime.now(),
        receivedAt = OffsetDateTime.now(),
        isMine = true,
        isDelivered = true,
        isRead = true,
        quotedMessageId = id
    )
)

@Preview
@Composable
private fun ConversationPreview() {
    ChatterTheme {
        Conversation(navigateBack = {})
    }
}
