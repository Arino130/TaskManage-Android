package com.ctp.taskmanageapp.common.utils

import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo

fun isEndTimeValid(taskInfo: TaskInfo): Boolean {
    return taskInfo.endTime.isAfter(taskInfo.startTime)
}
fun validateTaskInfo(taskInfo: TaskInfo): Pair<Boolean, Int> {
    return if (taskInfo.titleTask.trim().isEmpty()) {
        Pair(false, R.string.add_task_task_name_valid)
    } else if (taskInfo.content.trim().isEmpty()) {
        Pair(false, R.string.add_task_descriptions_valid)
    } else if (isEndTimeValid(taskInfo)) {
        Pair(false, R.string.add_task_end_time_error)
    } else {
        Pair(true, -1)
    }
}
