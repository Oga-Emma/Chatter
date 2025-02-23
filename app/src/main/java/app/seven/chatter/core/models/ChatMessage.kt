package app.seven.chatter.core.models

import java.time.OffsetDateTime
import java.util.UUID

data class ChatMessage (
    val id: UUID,
    val sender: MessageSender,
    val quotedMessageId: UUID? = null,
    val message: String,
    val sentAt: OffsetDateTime,
    val receivedAt: OffsetDateTime,
    val isMine: Boolean,
    val isDelivered: Boolean,
    val isRead: Boolean,
) {
    data class MessageSender(val id: UUID = UUID.randomUUID(), val fullName: String)
}
