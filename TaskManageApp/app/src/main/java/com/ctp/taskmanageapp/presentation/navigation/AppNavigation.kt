package com.ctp.taskmanageapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ctp.taskmanageapp.domain.models.filters.StatusTask
import com.ctp.taskmanageapp.domain.models.taskgroups.TaskGroupType
import com.ctp.taskmanageapp.presentation.extensions.navigateAndClearBackStack
import com.ctp.taskmanageapp.presentation.extensions.popBackStackTo
import com.ctp.taskmanageapp.presentation.screens.aboutus.AboutUsScreen
import com.ctp.taskmanageapp.presentation.screens.calendar.CalendarScreen
import com.ctp.taskmanageapp.presentation.screens.home.HomeScreen
import com.ctp.taskmanageapp.presentation.screens.managetasks.ManageTaskScreen
import com.ctp.taskmanageapp.presentation.screens.onboarding.OnBoardingScreen
import com.ctp.taskmanageapp.presentation.screens.settings.SettingsScreen
import com.ctp.taskmanageapp.presentation.screens.taskinfo.AddTaskScreen
import com.ctp.taskmanageapp.presentation.screens.taskinfo.DetailsTaskScreen
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel


@ExperimentalAnimationApi
@Composable
fun AppNavigation(mainViewModel: MainViewModel, hasSeenOnboarding: Boolean) {
    val navController = rememberNavController()
    val showBottomBar by mainViewModel.showBottomBar.collectAsState()
    val currentScreen: MutableState<BottomNavScreen> = remember {
        mutableStateOf(BottomNavScreen.Home)
    }
    if (showBottomBar) {
        val bottomBarState = remember { (mutableStateOf(true)) }
        Scaffold(
            modifier = Modifier
                .fillMaxHeight()
                .navigationBarsPadding(),
            bottomBar = {
                BottomNavigationBar(
                    tabs = getBottomNavScreen(),
                    onTabSelected = {
                        if (currentScreen != it) {
                            currentScreen.value = it
                            navController.navigate(currentScreen.value.route.name)
                        }
                    },
                    onAddClick = {
                        navController.navigate(Routes.AddTask.name)
                    },
                    selectTab = currentScreen.value,
                    bottomBarState = bottomBarState.value
                )
            }
        ) { innerPadding ->
            NavigationController(mainViewModel, navController, innerPadding, hasSeenOnboarding)
        }
    } else {
        Box(
            modifier = Modifier
                .displayCutoutPadding()
                .navigationBarsPadding()
        ) {
            NavigationController(
                mainViewModel, navController,
                hasSeenOnboarding = hasSeenOnboarding
            )
        }
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    LaunchedEffect(navBackStackEntry) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        getBottomNavScreen().firstOrNull {
            it.route.name == currentRoute?.substringBefore("/")
        }?.let { currentScreen.value = it }
    }
}

@ExperimentalAnimationApi
@Composable
fun NavigationController(
    mainViewModel: MainViewModel,
    navController: NavHostController,
    innerPadding: PaddingValues? = null,
    hasSeenOnboarding: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = if (hasSeenOnboarding) Routes.Home.name else Routes.OnBoarding.name,
        Modifier.padding(innerPadding ?: PaddingValues()),
        enterTransition = {
            fadeIn(animationSpec = tween(mainViewModel.enterTransition))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(mainViewModel.exitTransition))
        }
    ) {
        composable(route = Routes.OnBoarding.name) {
            OnBoardingScreen(
                mainViewModel = mainViewModel,
                onAddTask = {
                    navController.navigate(route = "${Routes.AddTask.name}/true")
                }
            )
        }

        composable(route = Routes.Home.name) {
            HomeScreen(
                mainViewModel = mainViewModel,
                onClickViewTask = {
                    mainViewModel.clearDataCalendarScreen()
                    navController.navigate(route = Routes.Calendar.name)
                },
                onClickGroupTask = { groupType, status ->
                    mainViewModel.filterGroupTypeLatest = TaskGroupType.values().firstOrNull {
                        it.name == groupType.takeIf { item -> item.isNotBlank() }
                    }
                    navController.navigate(route = "${Routes.ManageTasks.name}/$status")
                },
                onClickActionMore = {
                    navController.navigate(route = Routes.ManageTasks.name)
                }
            )
        }

        composable(route = Routes.Calendar.name) {
            CalendarScreen(mainViewModel = mainViewModel, onDetailsTask = {
                navController.navigate(route = "${Routes.DetailsTask.name}/$it")
            })
        }

        composable(route = Routes.ManageTasks.name) {
            ManageTaskScreen(mainViewModel = mainViewModel,
                onDetailsTask = {
                    navController.navigate(route = "${Routes.DetailsTask.name}/$it")
                }
            )
        }

        composable(
            route = "${Routes.ManageTasks.name}/{status_task}",
            arguments = listOf(
                navArgument("status_task") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { navBackStackEntry ->
            val statusTask = navBackStackEntry.arguments?.getString("status_task")
            ManageTaskScreen(mainViewModel = mainViewModel,
                defaultStatusTask = StatusTask.values().firstOrNull {
                    it.name == statusTask.takeIf { item -> item.toString().isNotBlank() }
                },
                onDetailsTask = {
                    navController.navigate(route = "${Routes.DetailsTask.name}/$it")
                }
            )
        }


        composable(route = Routes.Settings.name) {
            SettingsScreen(mainViewModel = mainViewModel) {
                navController.navigate(route = Routes.AboutUs.name)
            }
        }

        composable(route = Routes.AddTask.name) {
            AddTaskScreen(mainViewModel = mainViewModel, onBack = { isFirstOnBoarding ->
                if (isFirstOnBoarding) {
                    navController.navigateAndClearBackStack(Routes.Home.name)
                } else {
                    navController.popBackStack()
                }
            }
            )
        }

        composable(
            route = "${Routes.AddTask.name}/{isFirstOnBoarding}",
            arguments = listOf(navArgument("isFirstOnBoarding") {
                type = NavType.BoolType
                defaultValue = false
            })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getBoolean("isFirstOnBoarding")
                ?.let {
                    AddTaskScreen(
                        mainViewModel = mainViewModel,
                        isFirstOnBoarding = it,
                        onBack = { isFirstOnBoarding ->
                            if (isFirstOnBoarding) {
                                navController.navigateAndClearBackStack(Routes.Home.name)
                            } else {
                                navController.popBackStack()
                            }
                        }
                    )
                }
        }

        composable(
            route = "${Routes.DetailsTask.name}/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
                defaultValue = -1
            })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("id")?.let { taskId ->
                DetailsTaskScreen(mainViewModel = mainViewModel, taskId, onBack = {
                    navController.popBackStack()
                })
            }
        }

        composable(route = Routes.AboutUs.name) {
            AboutUsScreen(mainViewModel, onBack = {
                navController.popBackStack()
            })
        }
    }
}

fun onBackScreen(navController: NavHostController) {
    navController.popBackStackTo(
        navController.previousBackStackEntry?.destination?.route.toString()
    )
}

