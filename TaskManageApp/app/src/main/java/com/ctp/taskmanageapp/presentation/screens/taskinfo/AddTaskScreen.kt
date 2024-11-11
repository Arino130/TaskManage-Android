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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.domain.extensions.toLocalDateTimeWithCurrentTime
import com.ctp.taskmanageapp.domain.models.taskgroups.TaskGroupType
import com.ctp.taskmanageapp.domain.models.filters.StatusTask
import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_40_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_DEFAULT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_12_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.ui.theme.getColorSchemeBackground
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel
import com.ctp.taskmanageapp.widget.components.buttons.ButtonTMComponent
import com.ctp.taskmanageapp.widget.components.buttons.ButtonType
import com.ctp.taskmanageapp.widget.components.dropdowns.common.DropDownTM
import com.ctp.taskmanageapp.widget.components.dropdowns.datetime.DropDownDateTimeTM
import com.ctp.taskmanageapp.widget.components.dropdowns.models.DropDownTMModel
import com.ctp.taskmanageapp.widget.components.headers.HeaderSubScreen
import com.ctp.taskmanageapp.widget.components.inputs.InputTM
import com.ctp.taskmanageapp.widget.components.inputs.InputType
import java.time.LocalDateTime

@Composable
fun AddTaskScreen(
    mainViewModel: MainViewModel,
    isFirstOnBoarding: Boolean = false,
    onBack: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val plusEndHourDefault = remember { 1L }
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
    val startTime = remember { LocalDateTime.now() }
    val endTime = remember { LocalDateTime.now() }
    val taskInfo = remember {
        mutableStateOf(
            TaskInfo(
                taskGroupType = TaskGroupType.values().toList().first(),
                startTime = startTime,
                endTime = endTime,
                statusTask = StatusTask.TODO
            )
        )
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
            if (isFirstOnBoarding) {
                HeaderSubScreen(
                    titleId = R.string.add_task_screen_title,
                    onBackButtonIcon = null
                ) {}
            } else {
                HeaderSubScreen(titleId = R.string.add_task_screen_title) {
                    onBack(false)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(top = SPACE_DEFAULT_SIZE, bottom = SPACE_CONTENT_40_SIZE)
            ) {
                DropDownTM(taskGroupTypes, selectDefault = mainViewModel.filterGroupTypeLatest) {
                    taskInfo.value = taskInfo.value.copy(taskGroupType = it.rootData)
                }
                InputTM(
                    inputType = InputType.SINGLE_INPUT,
                    labelId = R.string.add_task_screen_task_name_field,
                    hintId = R.string.add_task_screen_task_name_field_hint
                ) {
                    taskInfo.value = taskInfo.value.copy(titleTask = it)
                }
                Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
                InputTM(
                    inputType = InputType.AREA_INPUT,
                    labelId = R.string.add_task_screen_description_field,
                    hintId = R.string.add_task_screen_description_field_hint
                ) {
                    taskInfo.value = taskInfo.value.copy(content = it)
                }
                Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
                DropDownDateTimeTM(
                    titleId = R.string.add_task_screen_start_datetime_title,
                    value = mainViewModel.filterDatetimeLatest?.toLocalDateTimeWithCurrentTime()
                ) {
                    taskInfo.value = taskInfo.value.copy(startTime = it)
                }
                Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
                DropDownDateTimeTM(
                    titleId = R.string.add_task_screen_end_datetime_title,
                    value = mainViewModel.filterDatetimeLatest?.toLocalDateTimeWithCurrentTime(),
                    plusHours = plusEndHourDefault
                ) {
                    taskInfo.value = taskInfo.value.copy(endTime = it)
                }
            }
        }
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
                mainViewModel.createTaskInfo(taskInfo.value) {
                    if (isFirstOnBoarding) { mainViewModel.onFinishedOnBoarding() }
                    onBack(isFirstOnBoarding)
                }
            }
        }
    }
}

@Preview
@Composable
fun AddTaskScreenPreview() {
    AddTaskScreen(MainViewModel(null, null, null)) {}
}