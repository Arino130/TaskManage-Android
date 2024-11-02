package com.ctp.taskmanageapp.domain.models

import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo

data class TaskGroup(
    val titleGroup: String,
    val taskGroupType: TaskGroupType,
    val taskInfo: List<TaskInfo> = listOf()
) {
    val taskCounts: Int
        get () = 10 // TODO: MOCK DATA
    val progressNumber: Int
        get () = 80 // TODO: MOCK DATA
}