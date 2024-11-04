package com.ctp.taskmanageapp.presentation.screens.taskinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.domain.models.TaskGroupType
import com.ctp.taskmanageapp.presentation.common.SPACE_DEFAULT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_12_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel
import com.ctp.taskmanageapp.widget.components.buttons.ButtonTMComponent
import com.ctp.taskmanageapp.widget.components.buttons.ButtonType
import com.ctp.taskmanageapp.widget.components.dropdowns.common.DropDownTM
import com.ctp.taskmanageapp.widget.components.dropdowns.datetime.DropDownDateTimeTM
import com.ctp.taskmanageapp.widget.components.dropdowns.models.DropDownTMModel
import com.ctp.taskmanageapp.widget.components.headers.HeaderSubScreen
import com.ctp.taskmanageapp.widget.components.inputs.InputTM
import com.ctp.taskmanageapp.widget.components.inputs.InputType

@Composable
fun AddTaskScreen(mainViewModel: MainViewModel, onBack: () -> Unit) {
    val context = LocalContext.current
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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = SPACE_SMALL_12_SIZE, vertical = SPACE_DEFAULT_SIZE)
        ) {
            HeaderSubScreen(titleId = R.string.add_task_screen_title) {
                onBack()
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
            Spacer(modifier = Modifier.weight(1f))
            ButtonTMComponent(
                titleButton = context.getString(R.string.add_task_screen_button_save_title),
                buttonType = ButtonType.Primary
            ) {
                //TODO: OnClick Add Task
            }
        }
    }
}

@Preview
@Composable
fun AddTaskScreenPreview() {
    AddTaskScreen(MainViewModel(null)) {}
}