package com.ctp.taskmanageapp.presentation.screens.managetasks

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
import com.ctp.taskmanageapp.domain.models.taskgroups.TaskGroupType
import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_4_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.screens.calendar.component.CalendarTaskItem
import com.ctp.taskmanageapp.presentation.screens.calendar.getFilteredTaskInfoData
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel
import com.ctp.taskmanageapp.widget.components.buttons.SegmentedControl
import com.ctp.taskmanageapp.widget.components.buttons.model.SegmentModel
import com.ctp.taskmanageapp.widget.components.dialogs.DialogNotify
import com.ctp.taskmanageapp.widget.components.dialogs.models.DialogInfo
import com.ctp.taskmanageapp.widget.components.dialogs.models.DialogType
import com.ctp.taskmanageapp.widget.components.headers.HeaderScreen
import com.ctp.taskmanageapp.widget.components.swipe.SwipeActionBox

@Composable
fun ManageTaskScreen(
    mainViewModel: MainViewModel,
    defaultStatusTask: StatusTask? = null,
    onDetailsTask: (Int) -> Unit
) {
    LaunchedEffect(Unit) {
        mainViewModel.toggleBottomBar(true)
    }
    val filterGroupTypes: List<SegmentModel> = remember {
        listOf(
            SegmentModel(textId = R.string.all_type).apply {
                isSelected = true
            }
        ) + TaskGroupType.values().map { item ->
            SegmentModel(textId = item.typeTitleId).apply {
                isSelected = false
            }
        }
    }
    val showConfirmDoneTask: MutableState<TaskInfo?> = remember { mutableStateOf(null) }
    val isFirstLoad = remember { mutableStateOf(true) }
    val filterGroupTypeSelected = remember {
        mutableStateOf(mainViewModel.filterGroupTypeLatest?.let { filterGroupType ->
            filterGroupTypes.firstOrNull {
                it.textId == filterGroupType.typeTitleId
            }
        } ?: filterGroupTypes.firstOrNull { it.isSelected })
    }
    val filterStatus: List<SegmentModel> = remember {
        StatusTask.values().toList().mapIndexed { index, item ->
            SegmentModel(textId = item.fullNameId).apply {
                isSelected = (index == 0)
            }
        }
    }
    val filterStatusSelected = remember {
        mutableStateOf(defaultStatusTask?.let {
            filterStatus.firstOrNull {
                it.textId == defaultStatusTask.fullNameId
            }
        } ?: filterStatus.firstOrNull { it.isSelected })
    }
    val taskData = remember {
        mutableStateOf(
            getFilteredTaskInfoData(
                mainViewModel,
                filterStatusSelected = filterStatusSelected.value,
                filterGroupTypeSelected = filterGroupTypeSelected.value
            )
        )
    }
    Column {
        HeaderScreen(titleId = R.string.manage_task_screen_title)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = SPACE_CONTENT_SIZE)
        ) {
            Box(
                modifier = Modifier.padding(top = SPACE_CONTENT_SIZE)
            ) {
                SegmentedControl(selectItem = filterGroupTypeSelected.value, filterGroupTypes) {
                    filterGroupTypeSelected.value = it
                    mainViewModel.filterGroupTypeLatest =
                        TaskGroupType.values().firstOrNull { item -> item.typeTitleId == it.textId }
                }
            }
            Box(
                modifier = Modifier.padding(top = SPACE_CONTENT_SIZE)
            ) {
                SegmentedControl(selectItem = filterStatusSelected.value, filterStatus) {
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
                                            filterStatusSelected = filterStatusSelected.value,
                                            filterGroupTypeSelected = filterGroupTypeSelected.value
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
                                filterStatusSelected = filterStatusSelected.value,
                                filterGroupTypeSelected = filterGroupTypeSelected.value
                            )
                            showConfirmDoneTask.value = null
                        }
                    }
                }
            )
        ) { showConfirmDoneTask.value = null }
    }
    LaunchedEffect(
        defaultStatusTask, mainViewModel.filterGroupTypeLatest
    ) {
        if (isFirstLoad.value) {
            defaultStatusTask?.let {
                filterStatusSelected.value =
                    filterStatus.firstOrNull { item -> item.textId == it.fullNameId }
            }
            mainViewModel.filterGroupTypeLatest?.let {
                filterGroupTypeSelected.value =
                    filterGroupTypes.firstOrNull { item -> item.textId == it.typeTitleId }
            }
            isFirstLoad.value = false
        }
    }
    LaunchedEffect(
        filterStatusSelected.value, filterGroupTypeSelected.value
    ) {
        taskData.value = getFilteredTaskInfoData(
            mainViewModel,
            filterStatusSelected = filterStatusSelected.value,
            filterGroupTypeSelected = filterGroupTypeSelected.value
        )
    }
}

@Preview
@Composable
fun ManageTaskScreenPreview() {
    ManageTaskScreen(MainViewModel(null, null, null)) {}
}
