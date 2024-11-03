package com.ctp.taskmanageapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ctp.taskmanageapp.presentation.screens.calendar.CalendarScreen
import com.ctp.taskmanageapp.presentation.screens.home.HomeScreen
import com.ctp.taskmanageapp.presentation.screens.onboarding.OnBoardingScreen
import com.ctp.taskmanageapp.presentation.screens.settings.SettingsScreen
import com.ctp.taskmanageapp.presentation.screens.taskinfo.AddTaskScreen
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel

@ExperimentalAnimationApi
@Composable
fun AppNavigation(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    val showBottomBar by mainViewModel.showBottomBar.collectAsState()
    var currentScreen: BottomNavScreen = BottomNavScreen.Home
    if (showBottomBar) {
        val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    tabs = getBottomNavScreen(),
                    onTabSelected = {
                        if (currentScreen != it) {
                            currentScreen = it
                            navController.navigate(currentScreen.route.name)
                        }
                    },
                    onAddClick = {
                        navController.navigate(Routes.AddTask.name)
                    },
                    selectTab = currentScreen,
                    bottomBarState = bottomBarState.value
                )
            }
        ) { innerPadding ->
            NavigationController(mainViewModel, navController, innerPadding)
        }
    } else {
        NavigationController(mainViewModel, navController)
    }
}

@ExperimentalAnimationApi
@Composable
fun NavigationController(
    mainViewModel: MainViewModel,
    navController: NavHostController,
    innerPadding: PaddingValues? = null
) {
    NavHost(
        navController = navController,
        startDestination = Routes.OnBoarding.name,
        Modifier.padding(innerPadding ?: PaddingValues()),
        enterTransition = {
            fadeIn(animationSpec = tween(700))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(700))
        }
    ) {
        composable(route = Routes.OnBoarding.name) {
            OnBoardingScreen(
                mainViewModel = mainViewModel,
                onFinishScreen = {
                    navController.navigate(Routes.Home.name) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Routes.Home.name) {
            HomeScreen(mainViewModel = mainViewModel)
        }

        composable(route = Routes.Calendar.name) {
            CalendarScreen(mainViewModel = mainViewModel)
        }

        composable(route = Routes.Settings.name) {
            SettingsScreen(mainViewModel = mainViewModel)
        }

        composable(route = Routes.AddTask.name) {
            AddTaskScreen(mainViewModel = mainViewModel)
        }
    }
}
