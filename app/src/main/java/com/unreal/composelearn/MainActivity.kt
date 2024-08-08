package com.unreal.composelearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.*
import com.unreal.composelearn.ui.screens.LoginScreen
import com.unreal.composelearn.ui.screens.OTPVerificationScreen
import com.unreal.composelearn.utils.Route

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge()
            val navController = rememberNavController()
            NavHost(navController, startDestination = Route.LoginScreen) {
                composable(Route.LoginScreen) { LoginScreen(navController)}
                composable("${Route.OTPVerificationScreen}/{phone}") { backStackEntry ->
                    val phone = backStackEntry.arguments?.getString("phone")
                    OTPVerificationScreen(phone!!)
                }
            }

        }
    }
}
