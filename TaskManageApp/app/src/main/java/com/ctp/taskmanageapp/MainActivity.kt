package com.ctp.taskmanageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ctp.taskmanageapp.presentation.common.SnackBarApp
import com.ctp.taskmanageapp.presentation.navigation.AppNavigation
import com.ctp.taskmanageapp.presentation.ui.theme.ThemeApp
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initApp()
    }

    private fun initApp() {
        installSplashScreen()
        enableEdgeToEdge()
        mainViewModel.initUI()

        setContent {
            ThemeApp {
                AppNavigation(mainViewModel)
                SnackBarApp()
            }
        }
    }
}
