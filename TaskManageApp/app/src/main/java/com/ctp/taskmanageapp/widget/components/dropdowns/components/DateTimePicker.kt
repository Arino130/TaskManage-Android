package com.ctp.taskmanageapp.widget.components.dropdowns.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.domain.extensions.get12HourFormat
import com.ctp.taskmanageapp.domain.extensions.getAmPm
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_4_SIZE
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun DateTimePicker(
    selectDateTime: LocalDateTime,
    onChanged: (LocalDateTime) -> Unit
) {
    val context = LocalContext.current
    val currentDateTime = LocalDateTime.now()
    val months = (1..12).toList()
    val years = (LocalDate.now().year..LocalDate.now().plusYears(10).year).toList()
    val hours = (1..12).toList()
    val minutes = (0..59).toList()
    val amPm = listOf("AM", "PM")
    var selectedDay by remember {
        mutableStateOf(selectDateTime.dayOfMonth.toString())
    }
    var selectedMonth by remember {
        mutableStateOf(selectDateTime.monthValue.toString())
    }
    var selectedYear by remember { mutableStateOf(selectDateTime.year.toString()) }
    var selectedHour by remember { mutableStateOf(selectDateTime.get12HourFormat()) }
    var selectedMinute by remember { mutableStateOf(selectDateTime.minute.toString()) }
    var selectedAmPm by remember { mutableStateOf(selectDateTime.getAmPm()) }
    val numberOfDaysInMonth = remember(selectedMonth, selectedYear) {
        val monthValue = selectedMonth.toInt()
        val yearValue = selectedYear.toInt()
        LocalDate.of(yearValue, monthValue, 1).lengthOfMonth()
    }
    LaunchedEffect(selectedDay, selectedMonth, selectedYear) {
        if (selectedDay.toInt() > numberOfDaysInMonth) {
            selectedDay = numberOfDaysInMonth.toString()
        }
    }
    LaunchedEffect(
        selectedDay,
        selectedMonth,
        selectedYear,
        selectedHour,
        selectedMinute,
        selectedAmPm
    ) {
        val hour = if (selectedAmPm == "PM" && selectedHour.toInt() != 12) {
            selectedHour.toInt() + 12
        } else if (selectedAmPm == "AM" && selectedHour.toInt() == 12) {
            0
        } else {
            selectedHour.toInt()
        }

        val newDateTime = LocalDateTime.of(
            selectedYear.toInt(),
            selectedMonth.toInt(),
            selectedDay.toInt(),
            hour,
            selectedMinute.toInt()
        )

        onChanged(newDateTime)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            context.getString(R.string.date_time_picker_title),
            style = MaterialTheme.typography.titleMedium,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SPACE_SMALL_4_SIZE),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val (validDays, validMonths) = if (selectedYear == currentDateTime.year.toString()) {
                val d = (currentDateTime.dayOfMonth..numberOfDaysInMonth).map {
                    it.toString()
                }
                val m = (months.filter { it >= currentDateTime.monthValue }.map { it.toString() })
                d to m
            } else {
                (1..numberOfDaysInMonth).map { it.toString() } to months.map { it.toString() }
            }
            selectedDay = validDays.firstOrNull { it == selectedDay } ?: validDays.first()
            selectedMonth = validMonths.firstOrNull { it == selectedMonth } ?: validMonths.first()
            WheelPicker(
                context = context,
                items = validDays,
                selectedItem = selectedDay,
                onItemSelected = { selectedDay = it },
                modifier = Modifier.weight(1f)
            )
            WheelPicker(
                context = context,
                items = validMonths,
                selectedItem = selectedMonth,
                onItemSelected = { selectedMonth = it },
                modifier = Modifier.weight(1f)
            )
            WheelPicker(
                context = context,
                items = years.map { it.toString() },
                selectedItem = selectedYear,
                onItemSelected = { selectedYear = it },
                modifier = Modifier.weight(1f)
            )
            WheelPicker(
                context = context,
                items = hours.map { it.toString() },
                selectedItem = selectedHour,
                onItemSelected = { selectedHour = it },
                modifier = Modifier.weight(1f)
            )
            WheelPicker(
                context = context,
                items = minutes.map { it.toString() },
                selectedItem = selectedMinute,
                onItemSelected = { selectedMinute = it },
                modifier = Modifier.weight(1f)
            )
            WheelPicker(
                context = context,
                items = amPm,
                selectedItem = selectedAmPm,
                onItemSelected = { selectedAmPm = it },
                modifier = Modifier.weight(1f)
            )
        }
    }
}
