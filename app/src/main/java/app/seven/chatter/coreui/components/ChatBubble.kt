package app.seven.chatter.coreui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.seven.chatter.core.models.ChatMessage


@Composable
fun ChatBubble(
    modifier: Modifier = Modifier,
    message: ChatMessage,
    quotedMessage: ChatMessage? = null
) {
    Row(
        modifier = modifier
            .padding(vertical = 4.dp)
            .padding(
                start = if (message.isMine) 60.dp else 0.dp,
                end = if (message.isMine) 0.dp else 60.dp
            )
            .fillMaxWidth().combinedClickable(
                onClick = {  },
                onLongClick = {
//                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
//                    contextMenuPhotoId = photo.id
                },
//                onLongClickLabel = stringResource(R.string.open_context_menu)
            ),
        horizontalArrangement = if (message.isMine) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.LightGray, shape = RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            if (quotedMessage != null) {
                Row {
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .drawBehind {
                                val strokeWidth = 2 * density * 0.7f
                                val y = size.height - strokeWidth / 2

                                drawLine(
                                    Color.Black,
                                    Offset(-20f, size.height - 20),
                                    Offset(-20f, 10f),
                                    strokeWidth
                                )
                            }
                    ) {
                        Text(
                            text = quotedMessage.sender.fullName,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = quotedMessage.message, fontSize = 12.sp, color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
            Text(text = message.message, fontSize = 14.sp)
        }
    }
}
