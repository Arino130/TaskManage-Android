package com.ctp.taskmanageapp.presentation.screens.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.domain.models.filters.StatusTask
import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_DEFAULT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_4_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.screens.calendar.component.CalendarScrollPicker
import com.ctp.taskmanageapp.presentation.screens.calendar.component.CalendarTaskItem
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel
import com.ctp.taskmanageapp.widget.components.buttons.SegmentedControl
import com.ctp.taskmanageapp.widget.components.buttons.model.SegmentModel
import com.ctp.taskmanageapp.widget.components.dialogs.DialogNotify
import com.ctp.taskmanageapp.widget.components.dialogs.models.DialogInfo
import com.ctp.taskmanageapp.widget.components.dialogs.models.DialogType
import com.ctp.taskmanageapp.widget.components.headers.HeaderScreen
import com.ctp.taskmanageapp.widget.components.swipe.SwipeActionBox
import java.time.LocalDate

@Composable
fun CalendarScreen(mainViewModel: MainViewModel, onDetailsTask: (Int) -> Unit) {
    LaunchedEffect(Unit) {
        mainViewModel.toggleBottomBar(true)
    }
    val filterTypes: List<SegmentModel> = remember {
        StatusTask.values().toList().mapIndexed { index, item ->
            SegmentModel(textId = item.fullNameId).apply {
                isSelected = (index == 0)
            }
        }
    }
    val showConfirmDoneTask: MutableState<TaskInfo?> = remember { mutableStateOf(null) }
    val filterStatusSelected = remember {
        mutableStateOf(mainViewModel.filterStatusLatest ?: filterTypes.first()) }
    val filterDatetimeSelected = remember {
        mutableStateOf(mainViewModel.filterDatetimeLatest ?: LocalDate.now()) }
    val taskData = remember {
        mutableStateOf(
            getFilteredTaskInfoData(
                mainViewModel,
                filterStatusSelected.value,
                filterDatetimeSelected.value
            )
        )
    }
    LaunchedEffect(filterStatusSelected.value, filterDatetimeSelected.value) {
        taskData.value = getFilteredTaskInfoData(
            mainViewModel,
            filterStatusSelected.value,
            filterDatetimeSelected.value
        )
        mainViewModel.filterStatusLatest = filterStatusSelected.value
        mainViewModel.filterDatetimeLatest = filterDatetimeSelected.value
    }
    Column {
        HeaderScreen(titleId = R.string.calendar_title)
        Spacer(modifier = Modifier.padding(top = SPACE_DEFAULT_SIZE))
        CalendarScrollPicker(value = filterDatetimeSelected.value) {
            taskData.value = listOf()
            filterDatetimeSelected.value = it
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = SPACE_CONTENT_SIZE)
        ) {
            Box(
                modifier = Modifier.padding(top = SPACE_CONTENT_SIZE)
            ) {
                SegmentedControl(selectItem = filterStatusSelected.value, filterTypes) {
                    taskData.value = listOf()
                    filterStatusSelected.value = it
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = SPACE_CONTENT_SIZE)
                    .verticalScroll(rememberScrollState())
            ) {
                taskData.value.forEach { item ->
                    Box(modifier = Modifier.padding(vertical = SPACE_SMALL_4_SIZE)) {
                        SwipeActionBox(item = item, onAction = {
                            onDetailsTask(it.id)
                        })
                        {
                            CalendarTaskItem(
                                item
                            ) {
                                if (it.statusTask == StatusTask.IN_PROGRESS) {
                                    showConfirmDoneTask.value = it
                                } else {
                                    mainViewModel.onNextStatusTask(it) {
                                        taskData.value = getFilteredTaskInfoData(
                                            mainViewModel,
                                            filterStatusSelected.value,
                                            filterDatetimeSelected.value
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.size(SPACE_SMALL_8_SIZE))
            }
        }
    }
    if (showConfirmDoneTask.value != null) {
        DialogNotify(
            DialogInfo(
                R.string.dialog_confirm_done_task_title,
                R.string.dialog_confirm_done_task_body,
                DialogType.CONFIRM,
                onConfirm = {
                    showConfirmDoneTask.value?.let {
                        mainViewModel.onNextStatusTask(it) {
                            taskData.value = getFilteredTaskInfoData(
                                mainViewModel,
                                filterStatusSelected.value,
                                filterDatetimeSelected.value
                            )
                            showConfirmDoneTask.value = null
                        }
                    }
                }
            )
        ) { showConfirmDoneTask.value = null }
    }
}

fun getFilteredTaskInfoData(
    mainViewModel: MainViewModel,
    filterStatusSelected: SegmentModel,
    filterDatetimeSelected: LocalDate
): List<TaskInfo> {
    return mainViewModel.getTaskInfoData(
        statusFilter = StatusTask.values().first {
            it.fullNameId == filterStatusSelected.textId
        },
        filterDate = filterDatetimeSelected
    )
}

@Preview
@Composable
fun CalendarScreenPreview() {
    CalendarScreen(MainViewModel(null)) {}
}
