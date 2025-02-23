package app.seven.chatter.features.auth.phonenumber

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.seven.chatter.coreui.theme.ChatterTheme
import com.eygraber.compose.country.code.picker.CountryCodePicker

@Composable
fun PhoneNumberScreen(modifier: Modifier = Modifier, onContinue: () -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    if (false) {
        CountryCodePicker(
            onClick = { country -> },
        )
    }

    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text("Welcome\nBack!", fontSize = 32.sp, lineHeight = 40.sp)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Enter your phone number to get started.")
        Spacer(modifier = Modifier.padding(36.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            shape = CircleShape,
            value = text,
            placeholder = { Text(text = "Phone Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            leadingIcon = {
               Row(
                   horizontalArrangement = Arrangement.Center,
                   verticalAlignment = Alignment.CenterVertically
               ) {
                   Text("+235")
                   IconButton(onClick = {}) {
                       Icon(Icons.Default.KeyboardArrowDown, "")
                   }
               }
            },
            onValueChange = { it ->
                text = it
            }
        )
        Spacer(modifier = Modifier.padding(16.dp))
        FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.filledTonalButtonColors()
                .copy(containerColor = Color.Black),
            onClick = {
                onContinue()
            },
        ) {
            Text("Continue")
        }
    }
}

@Preview
@Composable
private fun PhoneNumberScreenPreview() {
    ChatterTheme {
        PhoneNumberScreen(onContinue = {})
    }
}
