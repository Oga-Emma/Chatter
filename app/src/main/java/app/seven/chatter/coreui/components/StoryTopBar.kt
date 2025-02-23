package app.seven.chatter.coreui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.seven.chatter.R

@Composable
fun StoryTopBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        LazyRow {
            item {
                AddStoryIcon(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(start = 8.dp)
                )
            }

            items(5) {
                StoryTopBarItem(
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun StoryTopBarItem(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        CircleAvatar(
            size = 72
        ) {}
        Spacer(modifier = Modifier.height(8.dp))
        Text("Terry", fontSize = 13.sp)
    }
}

@Composable
fun AddStoryIcon(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        CircleAvatar(
            backgroundColor = Color.Transparent,
            size = 72,
            modifier = Modifier
                .clip(CircleShape)
                .background(color = Color.Transparent)
                .border(1.dp, color = Color.LightGray, shape = CircleShape)
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Rounded.Add,
                tint = Color.Gray,
                contentDescription = stringResource(R.string.add_story_icon)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.add_story), fontSize = 13.sp)
    }
}

@Preview
@Composable
private fun StoryTopBarPreview() {
    Scaffold { it ->
        StoryTopBar(
            modifier = Modifier.padding(it)
        )
    }
}