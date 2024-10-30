package com.ctp.taskmanageapp.presentation.screens.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.data.local.filters.StatusFilter
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_SIZE
import com.ctp.taskmanageapp.presentation.screens.calendar.component.CalendarScrollPicker
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel
import com.ctp.taskmanageapp.widget.components.buttons.SegmentedControl
import com.ctp.taskmanageapp.widget.components.buttons.model.SegmentModel
import com.ctp.taskmanageapp.widget.components.headers.HeaderScreen

@Composable
fun CalendarScreen(mainViewModel: MainViewModel) {
    val filterTypes: List<SegmentModel> = remember {
        StatusFilter.values().toList().mapIndexed { index, item ->
            SegmentModel(textId = item.fullNameId).apply {
                isSelected = (index == 0)
            }
        }
    }
    Column {
        HeaderScreen(titleId = R.string.calendar_title)
        CalendarScrollPicker()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(
                    paddingValues = PaddingValues(
                        horizontal = SPACE_CONTENT_SIZE
                    )
                )
        ) {
            Box(
                modifier = Modifier.padding(top = SPACE_CONTENT_SIZE)
            ) {
                SegmentedControl(filterTypes) {
                    // TODO: Click Filter
                }
            }
        }
    }
}

@Preview
@Composable
fun CalendarScreenPreview() {
    CalendarScreen(MainViewModel())
}
