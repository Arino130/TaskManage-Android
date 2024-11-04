package com.ctp.taskmanageapp.presentation.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun ThemeApp(contentCallBack: @Composable () -> Unit) {
    val view = LocalView.current
    val window = (view.context as Activity).window
    window.statusBarColor = getColorSchemeBackground().toArgb()
    WindowCompat.getInsetsController(
        window,
        view
    )
    MaterialTheme(
        content = { contentCallBack() }
    )
}

@Composable
fun getColorSchemeBackground(): Color {
    return MaterialTheme.colorScheme.background
}
