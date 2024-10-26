package com.ctp.taskmanageapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ctp.taskmanageapp.presentation.screens.onboarding.OnBoardingScreen

@ExperimentalAnimationApi
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.OnBoarding.name
    ) {
        composable(route = Routes.OnBoarding.name) {
            OnBoardingScreen()
        }
    }
}