package app.seven.chatter.features.auth.verifyphone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.seven.chatter.coreui.theme.ChatterTheme
import com.composeuisuite.ohteepee.OhTeePeeDefaults
import com.composeuisuite.ohteepee.OhTeePeeInput

@Composable
fun VerifyPhoneScreen(modifier: Modifier = Modifier, onVerifyPhoneNumber: () -> Unit) {
    // a mutable state to handle OTP value changes…
    var otpValue: String by remember { mutableStateOf("") }

    // this config will be used for each cell
    val defaultCellConfig = OhTeePeeDefaults.cellConfiguration(
        borderColor = Color.LightGray,
        borderWidth = 1.dp,
        shape = RoundedCornerShape(16.dp),
        textStyle = TextStyle(
            color = Color.Black
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Verify\nPhone Number", fontSize = 32.sp, lineHeight = 40.sp)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Enter the verification code sent to 08100.")
        Spacer(modifier = Modifier.padding(36.dp))
        OhTeePeeInput(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            value = otpValue,
            onValueChange = { newValue, isValid ->
                otpValue = newValue
            },
            configurations = OhTeePeeDefaults.inputConfiguration(
                cellsCount = 5,
                emptyCellConfig = defaultCellConfig,
                cellModifier = Modifier.size(48.dp),
            ),
        )
        Spacer(modifier = Modifier.padding(16.dp))
        FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.filledTonalButtonColors()
                .copy(containerColor = Color.Black),
            onClick = {
                onVerifyPhoneNumber()
            },
        ) {
            Text("Verify Phone Number")
        }
    }
}

@Preview
@Composable
private fun VerifyPhoneScreenPreview() {
    ChatterTheme {
        VerifyPhoneScreen(onVerifyPhoneNumber = {})
    }
}
