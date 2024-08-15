package com.unreal.composelearn.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unreal.composelearn.R
import com.unreal.composelearn.ui.theme.BlueHeading
import com.unreal.composelearn.ui.theme.DimLine
import com.unreal.composelearn.ui.theme.UIColor
import com.unreal.composelearn.utils.Route

@Composable
fun LoginScreen(navController: NavController) {
    var phone by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(UIColor),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.mobile_no),
            contentDescription = "mobile no. image",
            modifier = Modifier
                .size(200.dp)
                .weight(2f)
        )

        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(UIColor)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Text(
                text = "Login",
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 5.dp)
                    .fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight.W900,
                color = BlueHeading
            )

            OutlinedTextField(
                value = phone,
                onValueChange = {it: TextFieldValue ->
                    if (it.text.length <= 10 && it.text.all { char -> char.isDigit() }) {
                        phone = it
                    }
                },
                label = { Text(text = "Enter mobile number", fontSize = 16.sp) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
            Text(
                text = "OTP will be sent on entered number",
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 14.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = DimLine
            )

            Button(
                onClick = {
                    if (phone.text.isNotEmpty() && phone.text.matches(Regex("^[6789]\\d{9}$"))) {
                        navController.navigate("${Route.OTPVerificationScreen}/$phone")
                    } else {
                        Toast.makeText(context, "Invalid phone number", Toast.LENGTH_SHORT).show()
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(text = "Send OTP",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}