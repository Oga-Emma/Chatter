package app.seven.chatter.features.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.seven.chatter.ui.theme.ChatterTheme
import network.chaintech.cmpcountrycodepicker.model.CountryDetails
import network.chaintech.cmpcountrycodepicker.ui.CountryPickerBasicTextField

@Composable
fun PhoneNumberScreen(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    val selectedCountryState: MutableState<CountryDetails?> = remember {
        mutableStateOf(null)
    }
    var mobileNumber by remember {
        mutableStateOf("")
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
            onValueChange = { it ->
                text = it
            }
        )
        Spacer(modifier = Modifier.padding(16.dp))
        CountryPickerBasicTextField(
            mobileNumber = mobileNumber,
            defaultCountryCode = "de",
            onMobileNumberChange = {
//                mobileNumber = it
            },
            onCountrySelected = {
//                selectedCountryState.value = it
            },
            modifier = Modifier.fillMaxWidth(),
            defaultPaddingValues = PaddingValues(6.dp),
            showCountryFlag = true,
            showCountryPhoneCode = true,
            showCountryName = false,
            showCountryCode = false,
            showArrowDropDown = true,
            spaceAfterCountryFlag = 8.dp,
            spaceAfterCountryPhoneCode = 6.dp,
            spaceAfterCountryName = 6.dp,
            spaceAfterCountryCode = 6.dp,
            placeholder = {
                Text(text = "Phone Number")
            },
            focusedBorderThickness = 2.dp,
            unfocusedBorderThickness = 1.dp,
            shape = CircleShape,
//            verticalDividerColor = Color(0XFFDDDDDD),
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = Color(0XFFDDDDDD),
//                unfocusedBorderColor = Color(0XFFDDDDDD)
//            )
        )
        Spacer(modifier = Modifier.padding(16.dp))
        FilledTonalButton(
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.filledTonalButtonColors()
                .copy(containerColor = Color.Black),
            onClick = {},
        ) {
            Text("Continue")
        }
    }
}

@Preview
@Composable
private fun PhoneNumberScreenPreview() {
    ChatterTheme {
        PhoneNumberScreen()
    }
}
