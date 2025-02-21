package app.seven.chatter.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.seven.chatter.ui.theme.ChatterTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CAppbar(title: String, modifier: Modifier = Modifier) = TopAppBar(
    title = {
        Text(title)
    },
    modifier = modifier,
//    navigationIcon = TODO(),
//    actions = TODO(),
//    windowInsets = TODO(),
//    colors = TODO(),
//    scrollBehavior = TODO()
)

@Preview
@Composable
fun CAppBarPreview(modifier: Modifier = Modifier) {
    ChatterTheme {
        Scaffold(
            topBar = {
                CAppbar("Chatter")
            }
        ) { it -> Box(modifier = Modifier.padding(it)) }
    }
}