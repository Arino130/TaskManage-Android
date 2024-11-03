package com.ctp.taskmanageapp.domain.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.getAmPm(): String {
    val formatter = DateTimeFormatter.ofPattern("a")
    return this.format(formatter)
}

fun LocalDateTime.get12HourFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("h")
    return this.format(formatter)
}

fun LocalDateTime.formatDatetimeTask(): String {
    val formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy - hh:mm a")
    return this.format(formatter)
}
