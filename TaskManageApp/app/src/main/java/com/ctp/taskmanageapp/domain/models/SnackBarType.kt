package com.ctp.taskmanageapp.domain.models

import androidx.compose.ui.graphics.Color

data class SnackBarType(
    val messageId: Int,
    val type: Type
)

enum class Type(val actionColor: Color, val onSecondary: Color) {
    ERROR(Color.Red, Color.White), SUCCESS(Color.White, Color.Black)
}