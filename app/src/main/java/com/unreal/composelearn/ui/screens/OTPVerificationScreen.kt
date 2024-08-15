package com.unreal.composelearn.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.unreal.composelearn.R
import com.unreal.composelearn.ui.DashboardActivity
import com.unreal.composelearn.ui.theme.BlueHeading
import com.unreal.composelearn.ui.theme.UIColor

@Composable
fun OTPVerificationScreen(phone: String) {
    var otpValue by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(UIColor),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Image(
            painter = painterResource(id = R.drawable.otp_screen),
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

            ){
            Text(
                text = "Verify",
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 5.dp)
                    .fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight.W900,
                color = BlueHeading
            )

            OutlinedTextField(
                value = otpValue,
                onValueChange = {
                    if (it.text.length <= 4 && it.text.all { char -> char.isDigit() }) {
                        otpValue = it
                    }
                },
                label = { Text("Enter number") },
                visualTransformation = PasswordVisualTransformation(),
                trailingIcon = {
                    if (otpValue.text.isNotEmpty()) {
                        IconButton(onClick = { otpValue = TextFieldValue("") }) {
                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )

            Button(
                onClick = {
                    context.startActivity(Intent(context, DashboardActivity::class.java))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(text = "Verify OTP",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 16.sp
                )
            }

        }
    }
}