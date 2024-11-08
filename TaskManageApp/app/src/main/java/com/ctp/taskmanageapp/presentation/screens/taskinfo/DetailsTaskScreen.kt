package com.ctp.taskmanageapp.presentation.screens.taskinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.domain.models.TaskGroupType
import com.ctp.taskmanageapp.domain.models.filters.StatusTask
import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_40_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_DEFAULT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_12_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.ui.theme.getColorSchemeBackground
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel
import com.ctp.taskmanageapp.widget.components.buttons.ButtonLabel
import com.ctp.taskmanageapp.widget.components.buttons.ButtonTMComponent
import com.ctp.taskmanageapp.widget.components.buttons.ButtonType
import com.ctp.taskmanageapp.widget.components.dialogs.DialogNotify
import com.ctp.taskmanageapp.widget.components.dialogs.models.DialogInfo
import com.ctp.taskmanageapp.widget.components.dialogs.models.DialogType
import com.ctp.taskmanageapp.widget.components.dropdowns.common.DropDownTM
import com.ctp.taskmanageapp.widget.components.dropdowns.datetime.DropDownDateTimeTM
import com.ctp.taskmanageapp.widget.components.dropdowns.models.DropDownTMModel
import com.ctp.taskmanageapp.widget.components.headers.HeaderSubScreen
import com.ctp.taskmanageapp.widget.components.inputs.InputTM
import com.ctp.taskmanageapp.widget.components.inputs.InputType

@Composable
fun DetailsTaskScreen(
    mainViewModel: MainViewModel,
    taskId: Int,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val taskInfo: MutableState<TaskInfo?> = remember { mutableStateOf(null) }
    val taskInfoState: MutableState<TaskInfo?> = remember { mutableStateOf(null) }
    val isEditAllowed = remember { mutableStateOf(false) }
    val showConfirmDoneTask: MutableState<TaskInfo?> = remember { mutableStateOf(null) }
    val showConfirmDeleteTask: MutableState<TaskInfo?> = remember { mutableStateOf(null) }
    LaunchedEffect(taskId) {
        if (taskInfo.value == null) {
            mainViewModel.getTaskById(taskId) { task ->
                taskInfo.value = task
                taskInfoState.value = taskInfo.value
            }
        }
    }
    LaunchedEffect(Unit) {
        mainViewModel.toggleBottomBar(false)
    }
    val taskGroupTypes = remember {
        TaskGroupType.values().toList().map {
            DropDownTMModel(
                titleId = it.typeTitleId,
                iconId = it.typeIcon,
                rootData = it
            )
        }
    }
    val taskStatusTypes = remember {
        StatusTask.values().toList().filter { it.isDisplay }.sortedBy { it.sortIndex }.map {
            DropDownTMModel(
                titleId = it.fullNameId,
                iconId = it.iconStatus,
                rootData = it
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(getColorSchemeBackground())
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = SPACE_SMALL_12_SIZE)
                .padding(bottom = SPACE_CONTENT_40_SIZE)
        ) {
            val actionButtonIcon = taskInfo.value?.takeIf { !it.isTaskDone }?.let {
                if (isEditAllowed.value) R.drawable.ic_edit_off else R.drawable.ic_edit
            }
            HeaderSubScreen(
                titleId = R.string.details_task_screen_title,
                actionButtonIcon = actionButtonIcon,
                actionButton = {
                    if (taskInfo.value?.isTaskDone == false) {
                        isEditAllowed.value = !isEditAllowed.value
                        if (!isEditAllowed.value) {
                            taskInfoState.value = taskInfo.value
                        }
                    }
                }
            ) {
                onBack()
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(top = SPACE_DEFAULT_SIZE, bottom = SPACE_CONTENT_40_SIZE)
            ) {
                DropDownTM(
                    taskGroupTypes,
                    selectDefault = taskInfoState.value?.taskGroupType,
                    readOnly = !isEditAllowed.value
                ) {
                    taskInfoState.value = taskInfoState.value?.copy(taskGroupType = it.rootData)
                }
                DropDownTM(
                    taskStatusTypes,
                    selectDefault = taskInfoState.value?.statusTask,
                    readOnly = !isEditAllowed.value
                ) {
                    taskInfoState.value = taskInfoState.value?.copy(statusTask = it.rootData)
                }
                InputTM(
                    inputType = InputType.SINGLE_INPUT,
                    labelId = R.string.add_task_screen_task_name_field,
                    hintId = R.string.add_task_screen_task_name_field_hint,
                    value = taskInfoState.value?.titleTask,
                    readOnly = !isEditAllowed.value,
                    isFocusRequester = false
                ) {
                    taskInfoState.value = taskInfoState.value?.copy(titleTask = it)
                }
                Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
                InputTM(
                    inputType = InputType.AREA_INPUT,
                    labelId = R.string.add_task_screen_description_field,
                    hintId = R.string.add_task_screen_description_field_hint,
                    value = taskInfoState.value?.content,
                    readOnly = !isEditAllowed.value,
                    isFocusRequester = false
                ) {
                    taskInfoState.value = taskInfoState.value?.copy(content = it)
                }
                Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
                DropDownDateTimeTM(
                    titleId = R.string.add_task_screen_start_datetime_title,
                    readOnly = !isEditAllowed.value,
                    value = taskInfoState.value?.startTime
                ) {
                    taskInfoState.value = taskInfoState.value?.copy(startTime = it)
                }
                Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
                DropDownDateTimeTM(
                    titleId = R.string.add_task_screen_end_datetime_title,
                    readOnly = !isEditAllowed.value,
                    value = taskInfoState.value?.endTime
                ) {
                    taskInfoState.value = taskInfoState.value?.copy(endTime = it)
                }
                Spacer(modifier = Modifier.padding(top = SPACE_CONTENT_SIZE))
                if (!isEditAllowed.value) {
                    ButtonLabel(R.string.delete_task_title) {
                        showConfirmDeleteTask.value = taskInfoState.value
                    }
                }

            }
        }
        if (isEditAllowed.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(SPACE_SMALL_12_SIZE),
                contentAlignment = Alignment.BottomCenter
            ) {
                ButtonTMComponent(
                    titleButton = context.getString(R.string.add_task_screen_button_save_title),
                    buttonType = ButtonType.Primary
                ) {
                    if (taskInfo.value?.statusTask != StatusTask.DONE
                        && taskInfoState.value?.statusTask == StatusTask.DONE
                    ) {
                        showConfirmDoneTask.value = taskInfoState.value
                    } else {
                        updateTaskInfo(mainViewModel, taskInfoState.value) {
                            onBack()
                        }
                    }
                }
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
                    updateTaskInfo(mainViewModel, taskInfoState.value) { onBack() }
                }
            )
        ) { showConfirmDoneTask.value = null }
    }

    if (showConfirmDeleteTask.value != null) {
        DialogNotify(
            DialogInfo(
                R.string.dialog_confirm_delete_task_title,
                R.string.dialog_confirm_delete_task_body,
                DialogType.CONFIRM,
                onConfirm = {
                    taskInfo.value?.let { value ->
                        mainViewModel.removeTaskInfo(value) {
                            onBack()
                        }
                    }
                }
            )
        ) { showConfirmDeleteTask.value = null }
    }
}

private fun updateTaskInfo(mainViewModel: MainViewModel, taskInfo: TaskInfo?, onSuccess: () -> Unit) {
    taskInfo?.let { value ->
        mainViewModel.updateStatus(
            value,
            value.statusTask
        ) {
            onSuccess()
        }
    }
}

@Preview
@Composable
fun DetailsTaskScreenPreview() {
    DetailsTaskScreen(
        MainViewModel(null, null), -1
    ) {}
}
