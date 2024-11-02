package com.ctp.taskmanageapp.presentation.screens.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.domain.models.filters.StatusTask
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.screens.calendar.component.CalendarScrollPicker
import com.ctp.taskmanageapp.presentation.screens.calendar.component.CalendarTaskItem
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel
import com.ctp.taskmanageapp.widget.components.buttons.SegmentedControl
import com.ctp.taskmanageapp.widget.components.buttons.model.SegmentModel
import com.ctp.taskmanageapp.widget.components.headers.HeaderScreen
import java.time.LocalDate

@Composable
fun CalendarScreen(mainViewModel: MainViewModel) {
    val filterTypes: List<SegmentModel> = remember {
        StatusTask.values().toList().mapIndexed { index, item ->
            SegmentModel(textId = item.fullNameId).apply {
                isSelected = (index == 0)
            }
        }
    }
    val filterStatusSelected = remember { mutableStateOf(filterTypes.first()) }
    val filterDatetimeSelected = remember { mutableStateOf(LocalDate.now()) }
    Column {
        HeaderScreen(titleId = R.string.calendar_title)
        CalendarScrollPicker {
            filterDatetimeSelected.value = it
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(
                    paddingValues = PaddingValues(
                        vertical = SPACE_CONTENT_SIZE,
                        horizontal = SPACE_CONTENT_SIZE
                    )
                )
        ) {
            Box(
                modifier = Modifier.padding(vertical = SPACE_CONTENT_SIZE)
            ) {
                SegmentedControl(filterTypes) {
                    filterStatusSelected.value = it
                }
            }
            Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                mainViewModel.getTaskInfoData(
                    statusFilter = StatusTask.values().toList().first {
                        it.fullNameId == filterStatusSelected.value.textId
                    },
                    filterDate = filterDatetimeSelected.value
                ).forEach { item ->
                    CalendarTaskItem(
                        item
                    ) {}
                }
            }
        }
    }
}

@Preview
@Composable
fun CalendarScreenPreview() {
    CalendarScreen(MainViewModel(null))
}
