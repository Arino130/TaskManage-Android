package com.ctp.taskmanageapp.presentation.screens.calendar.component

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.presentation.common.ELEVATION_DEFAULT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_12_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.common.h3TextStyle
import com.ctp.taskmanageapp.presentation.common.h4TextStyle
import com.ctp.taskmanageapp.presentation.extensions.getColorFromResources
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import kotlinx.coroutines.launch

data class CalendarDate(val day: String, val month: String, val weekday: String)

@Composable
fun CalendarScrollPicker() {
    val context = LocalContext.current
    val today = LocalDate.now()
    val dates = generateDateList(today)
    val initialCenterIndex = dates.size / 2
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var selectedDateIndex by remember { mutableStateOf(initialCenterIndex) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidth = 60.dp
    val centerOffset =
        with(LocalDensity.current) { (screenWidth / 2 - cardWidth / 2).toPx().toInt() }

    // Center the list on the initial selected date
    LaunchedEffect(Unit) {
        listState.scrollToItem(initialCenterIndex, -centerOffset)
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(SPACE_SMALL_8_SIZE),
    ) {
        itemsIndexed(dates) { index, date ->
            CardCalendarItem(
                context = context,
                day = date.day,
                month = date.month,
                weekday = date.weekday,
                isSelected = index == selectedDateIndex,
                onClick = {
                    selectedDateIndex = index
                    coroutineScope.launch {
                        listState.animateScrollToItem(index, centerOffset)
                    }
                }
            )
        }
    }
}

fun generateDateList(
    today: LocalDate,
    daysBefore: Int = 15,
    daysAfter: Int = 15
): List<CalendarDate> {
    val dates = mutableListOf<CalendarDate>()
    for (i in -daysBefore..daysAfter) {
        val date = today.plusDays(i.toLong())
        dates.add(
            CalendarDate(
                day = date.dayOfMonth.toString(),
                month = date.month.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                weekday = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
            )
        )
    }
    return dates
}

@Composable
fun CardCalendarItem(
    context: Context,
    day: String,
    month: String,
    weekday: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected)
        context.getColorFromResources(R.color.button_background_primary)
    else Color.White
    val textColor = if (isSelected) Color.White
    else context.getColorFromResources(R.color.text_blank_color)

    Card(
        modifier = Modifier
            .width(66.dp)
            .height(100.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(ELEVATION_DEFAULT_SIZE),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = SPACE_SMALL_8_SIZE),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = month, color = textColor, style = h4TextStyle,
                fontWeight = FontWeight.Normal
            )
            Text(
                modifier = Modifier.padding(
                    paddingValues = PaddingValues(vertical = SPACE_SMALL_12_SIZE)
                ),
                text = day,
                color = textColor,
                style = h3TextStyle,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = weekday, color = textColor, style = h4TextStyle,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview
@Composable
fun CalendarScrollPickerPreview() {
    CalendarScrollPicker()
}