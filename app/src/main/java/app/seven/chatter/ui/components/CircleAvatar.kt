package app.seven.chatter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CircleAvatar(modifier: Modifier = Modifier,
                 backgroundColor: Color = Color.LightGray,
                 size: Int = 50,
                 child: @Composable () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .background(color = backgroundColor)
            .size(size.dp)
    ) {
        child()
    }
}

@Preview
@Composable
private fun CircleAvatarPreview() {
    Surface {
        CircleAvatar {

        }
    }
}