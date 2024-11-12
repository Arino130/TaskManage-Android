package com.ctp.zentasks.presentation.extensions

import androidx.navigation.NavController

fun NavController.navigateAndClearBackStack(route: String) {
    this.navigate(route) {
        popUpTo(0) { inclusive = true }
    }
}

fun NavController.popBackStackTo(route: String, inclusive: Boolean = false) {
    this.popBackStack(route, inclusive = inclusive)
}
