package com.ctp.zentasks.presentation.screens.calendar.component

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
import com.ctp.zentasks.R
import com.ctp.zentasks.presentation.common.ELEVATION_DEFAULT_SIZE
import com.ctp.zentasks.presentation.common.SPACE_SMALL_12_SIZE
import com.ctp.zentasks.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.zentasks.presentation.common.h3TextStyle
import com.ctp.zentasks.presentation.common.h4TextStyle
import com.ctp.zentasks.presentation.extensions.getColorFromResources
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import kotlinx.coroutines.launch

@Composable
fun CalendarScrollPicker(
    value: LocalDate = LocalDate.now(),
    onClickDate: (LocalDate) -> Unit
) {
    val context = LocalContext.current
    val dates = generateDateList(value)
    val initialCenterIndex = dates.size / 2
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidth = 60.dp
    val centerOffset =
        with(LocalDensity.current) { (screenWidth / 2 - cardWidth / 2).toPx().toInt() }

    // Center the list on the initial selected date
    LaunchedEffect(Unit, value) {
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
                localDate = date,
                isSelected = date == value,
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(index, -centerOffset)
                    }
                    onClickDate(it)
                }
            )
        }
    }
}

fun generateDateList(
    today: LocalDate,
    daysBefore: Int = 15,
    daysAfter: Int = 15
): List<LocalDate> {
    val dates = mutableListOf<LocalDate>()
    for (i in -daysBefore..daysAfter) {
        val date = today.plusDays(i.toLong())
        dates.add(date)
    }
    return dates
}

@Composable
fun CardCalendarItem(
    context: Context,
    localDate: LocalDate,
    isSelected: Boolean,
    onClick: (LocalDate) -> Unit
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
            .clickable { onClick(localDate) },
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
                text = localDate.month.getDisplayName(
                    TextStyle.SHORT, Locale.getDefault()
                ),
                color = textColor, style = h4TextStyle,
                fontWeight = FontWeight.Normal
            )
            Text(
                modifier = Modifier.padding(vertical = SPACE_SMALL_12_SIZE),
                text = localDate.dayOfMonth.toString(),
                color = textColor,
                style = h3TextStyle,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = if (localDate == LocalDate.now()) {
                    context.getString(R.string.today)
                } else {
                    localDate.dayOfWeek.getDisplayName(
                        TextStyle.SHORT, Locale.getDefault()
                    )
                },
                color = textColor, style = h4TextStyle,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview
@Composable
fun CalendarScrollPickerPreview() {
    CalendarScrollPicker {}
}