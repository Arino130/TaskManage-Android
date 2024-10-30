package com.ctp.taskmanageapp.presentation.screens.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.data.local.taskgroups.TaskGroup
import com.ctp.taskmanageapp.data.local.taskgroups.TaskGroupType
import com.ctp.taskmanageapp.presentation.common.SPACE_DEFAULT_SIZE

@Composable
fun TaskGroupListView(taskGroups: List<TaskGroup>) {
    Column {
        taskGroups.forEach { item ->
            Box(
                modifier = Modifier.padding(
                    paddingValues = PaddingValues(bottom = SPACE_DEFAULT_SIZE)
                )
            ) {
                CardGroupTaskItem(item.taskGroupType, item.taskCounts, item.progressNumber)
            }
        }
    }
}

@Preview
@Composable
fun TaskGroupListViewPreview() {
    TaskGroupListView(
        listOf(
            TaskGroup(
                titleGroup = "Grocery shopping app design",
                taskGroupType = TaskGroupType.OfficeProject
            ),
            TaskGroup(
                titleGroup = "Grocery shopping app design",
                taskGroupType = TaskGroupType.PersonalProject
            ),
            TaskGroup(
                titleGroup = "Grocery shopping app design",
                taskGroupType = TaskGroupType.OfficeProject
            ),
            TaskGroup(
                titleGroup = "Grocery shopping app design",
                taskGroupType = TaskGroupType.PersonalProject
            ),
            TaskGroup(
                titleGroup = "Grocery shopping app design",
                taskGroupType = TaskGroupType.OfficeProject
            )
        )
    )
}