package com.ctp.zentasks.presentation.extensions

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat

fun Context.getColorFromResources(colorResId: Int): Color {
    return Color(ContextCompat.getColor(this, colorResId))
}