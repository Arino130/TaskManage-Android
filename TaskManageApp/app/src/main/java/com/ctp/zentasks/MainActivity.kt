package com.ctp.zentasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ctp.zentasks.presentation.navigation.AppNavigation
import com.ctp.zentasks.presentation.ui.theme.ThemeApp
import com.ctp.zentasks.presentation.viewmodels.MainViewModel
import com.ctp.zentasks.widget.components.snackbar.SnackBarApp
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
                AppNavigation(mainViewModel, mainViewModel.shouldSkipOnboarding())
                SnackBarApp()
            }
        }
    }
}
