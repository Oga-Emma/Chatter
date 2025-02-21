package app.seven.chatter.features.app.chat_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.seven.chatter.ui.components.CircleAvatar

@Composable
fun ActiveChatList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(10) {
            ActiveChatListItem()
        }
    }
}

@Composable
fun ActiveChatListItem(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleAvatar(
            size = 60
        ) { }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "Angel Curtis", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Please help me find a good monitor",
                fontSize = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
                color = Color.Gray
            )
        }
        Column(
            horizontalAlignment = Alignment.End,
        ) {
            Text(text = "02:22", fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(Color.Yellow, shape = CircleShape).padding(2.dp),
            ) {
                Text(
                    text = "2",
                    fontSize = 13.sp,
                )
            }
        }
    }
}

@Preview
@Composable
private fun ActiveChatListPreview() {
    Scaffold { it ->
        ActiveChatList(
            modifier = Modifier.padding(it).padding(horizontal = 16.dp)
        )
    }
}
