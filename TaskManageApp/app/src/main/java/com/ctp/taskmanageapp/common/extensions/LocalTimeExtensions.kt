package com.ctp.taskmanageapp.common.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.getFormat12Hour(): String {
    return try {
        this.format(DateTimeFormatter.ofPattern("h:mm a"))
    } catch (ex: Exception) {
        "Unknown"
    }
}

fun LocalDateTime.getFormatDate(): String {
    return try {
        this.format(DateTimeFormatter.ofPattern("dd MMM, yyyy"))
    } catch (ex: Exception) {
        "Unknown"
    }
}
