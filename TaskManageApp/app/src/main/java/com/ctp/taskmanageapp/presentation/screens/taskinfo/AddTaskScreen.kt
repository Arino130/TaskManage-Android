package com.ctp.taskmanageapp.presentation.screens.taskinfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.domain.models.TaskGroupType
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_12_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel
import com.ctp.taskmanageapp.widget.components.dropdowns.common.DropDownTM
import com.ctp.taskmanageapp.widget.components.dropdowns.datetime.DropDownDateTimeTM
import com.ctp.taskmanageapp.widget.components.dropdowns.models.DropDownTMModel
import com.ctp.taskmanageapp.widget.components.headers.HeaderSubScreen
import com.ctp.taskmanageapp.widget.components.inputs.InputTM
import com.ctp.taskmanageapp.widget.components.inputs.InputType

@Composable
fun AddTaskScreen(mainViewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        mainViewModel.toggleBottomBar(true)
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
    Column(
        modifier = Modifier
            .padding(horizontal = SPACE_SMALL_12_SIZE)
    ) {
        HeaderSubScreen(titleId = R.string.add_task_screen_title) {
            // TODO: On back screen
        }
        DropDownTM(taskGroupTypes) {
            // TODO: Select Group Type
        }
        InputTM(
            inputType = InputType.SINGLE_INPUT,
            labelId = R.string.add_task_screen_task_name_field,
            hintId = R.string.add_task_screen_task_name_field_hint
        ) {}
        Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
        InputTM(
            inputType = InputType.AREA_INPUT,
            labelId = R.string.add_task_screen_description_field,
            hintId = R.string.add_task_screen_description_field_hint
        ) {}
        Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
        DropDownDateTimeTM(titleId = R.string.add_task_screen_start_datetime_title) {
            // TODO: Onchange start datetime
        }
        Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
        DropDownDateTimeTM(titleId = R.string.add_task_screen_end_datetime_title) {
            // TODO: Onchange end datetime
        }
    }
}

@Preview
@Composable
fun AddTaskScreenPreview() {
    AddTaskScreen(MainViewModel(null))
}