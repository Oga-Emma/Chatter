package app.seven.chatter.features.app.conversation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.seven.chatter.R
import app.seven.chatter.ui.components.CircleAvatar
import app.seven.chatter.ui.theme.ChatterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Conversation(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
//            TopAppBar()
//            ConversationAppbar()
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}

@Composable
fun ConversationAppbar(modifier: Modifier = Modifier) {
    Column {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft, "")
            Spacer(modifier = Modifier.padding(4.dp))
            CircleAvatar(
                size = 56
            ) { }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = "Angel Curtis", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Text(
                    text = "Online",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row {
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
        }
        HorizontalDivider()
    }
}

@Preview
@Composable
private fun ConversationPreview() {
    ChatterTheme {
        Conversation()
    }
}
