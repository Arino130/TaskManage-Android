package com.ctp.taskmanageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.MaterialTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ctp.taskmanageapp.presentation.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initApp()
    }

    private fun initApp() {
        installSplashScreen()
        enableEdgeToEdge()

        setContent {
            MaterialTheme(
                content = { AppNavigation() }
            )
        }
    }
}
