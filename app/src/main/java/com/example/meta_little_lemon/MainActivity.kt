package com.example.meta_little_lemon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meta_little_lemon.ui.components.Home
import com.example.meta_little_lemon.ui.components.Onboarding
import com.example.meta_little_lemon.ui.components.Profile
import com.example.meta_little_lemon.ui.theme.Meta_little_lemonTheme

/*
Modifier : relation with other ui eliments
 */
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Meta_little_lemonTheme {
                // A surface container using the 'background' color from the theme
                Start() { this }
            }
        }
    }


    @Composable
    fun Start(contextProvider: () -> Context) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "onBoarding") {
            composable("onBoarding") { Onboarding(contextProvider(), navController) }
            composable("Home") { Home(navController, contextProvider) }
            composable("Profile") { Profile(contextProvider(), navController) }
        }
    }
}





