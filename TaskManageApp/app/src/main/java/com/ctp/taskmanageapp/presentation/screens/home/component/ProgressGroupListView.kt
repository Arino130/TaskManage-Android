package com.ctp.taskmanageapp.presentation.screens.home.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.domain.models.taskgroups.TaskGroup
import com.ctp.taskmanageapp.domain.models.taskgroups.TaskGroupType
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE

@Composable
fun ProgressGroupListView(taskGroups: List<TaskGroup>, onClickTaskGroup: (TaskGroupType) -> Unit) {
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        taskGroups.forEach { item ->
            Box(
                modifier = Modifier.padding(
                    paddingValues = PaddingValues(end = SPACE_SMALL_8_SIZE)
                )
            ) {
                CardTaskGroup(
                    groupType = item.taskGroupType,
                    item.titleGroup,
                    progress = item.progressNumber
                ) {
                    onClickTaskGroup(it)
                }
            }
        }
    }
}

@Preview
@Composable
fun ProgressGroupListViewPreview() {
    ProgressGroupListView(
        listOf(
            TaskGroup(
                titleGroup = "Grocery shopping app design by Phuong",
                taskGroupType = TaskGroupType.OfficeProject
            ),
            TaskGroup(
                titleGroup = "Grocery shopping app design by Phuong",
                taskGroupType = TaskGroupType.PersonalProject
            ),
            TaskGroup(
                titleGroup = "Grocery shopping app design by Phuong",
                taskGroupType = TaskGroupType.OfficeProject
            ),
            TaskGroup(
                titleGroup = "Grocery shopping app design by Phuong",
                taskGroupType = TaskGroupType.PersonalProject
            ),
            TaskGroup(
                titleGroup = "Grocery shopping app design by Phuong",
                taskGroupType = TaskGroupType.OfficeProject
            )
        )
    ) {}
}
