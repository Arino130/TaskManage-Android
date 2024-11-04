package com.ctp.taskmanageapp.presentation.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun ThemeApp(contentCallBack: @Composable () -> Unit) {
    val view = LocalView.current
    val window = (view.context as Activity).window
    window.statusBarColor = MaterialTheme.colorScheme.background.toArgb()
    WindowCompat.getInsetsController(
        window,
        view
    )
    MaterialTheme(
        content = { contentCallBack() }
    )
}