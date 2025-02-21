package app.seven.chatter.features.app.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.seven.chatter.R
import app.seven.chatter.features.app.chat_list.ActiveChatList
import app.seven.chatter.features.app.story.StoryTopBar

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        StoryTopBar()
        Spacer(modifier = Modifier.size(24.dp))
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Text(
                text = stringResource(R.string.chats),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Rounded.MoreHoriz, contentDescription = "More icon")
        }
        Spacer(modifier = Modifier.size(8.dp))
        ActiveChatList(
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}