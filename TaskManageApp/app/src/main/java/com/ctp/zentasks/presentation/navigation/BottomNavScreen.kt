package com.ctp.zentasks.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ctp.zentasks.R

sealed class BottomNavScreen(
    val route: Routes,
    @StringRes val label: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unSelectedIcon: Int
) {
    data object Home :
        BottomNavScreen(
            Routes.Home, R.string.home_label_nav,
            R.drawable.ic_nav_home,
            R.drawable.ic_nav_home
        )

    data object Calendar :
        BottomNavScreen(
            Routes.Calendar,
            R.string.calendar_label_nav,
            R.drawable.ic_nav_calendar,
            R.drawable.ic_nav_calendar
        )

    data object ManageTasks :
        BottomNavScreen(
            Routes.ManageTasks,
            R.string.manage_tasks_label_nav,
            R.drawable.ic_nav_task,
            R.drawable.ic_nav_task
        )

    data object Settings :
        BottomNavScreen(
            Routes.Settings, R.string.setting_label_nav,
            R.drawable.ic_nav_setting,
            R.drawable.ic_nav_setting
        )
}

fun getBottomNavScreen(): List<BottomNavScreen> = listOf(
    BottomNavScreen.Home,
    BottomNavScreen.Calendar,
    BottomNavScreen.ManageTasks,
    BottomNavScreen.Settings
)
