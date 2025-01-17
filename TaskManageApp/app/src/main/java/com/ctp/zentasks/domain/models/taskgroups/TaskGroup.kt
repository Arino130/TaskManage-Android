package com.ctp.zentasks.domain.models.taskgroups

import com.ctp.zentasks.domain.models.filters.StatusTask
import com.ctp.zentasks.domain.models.tasks.TaskInfo

data class TaskGroup(
    val titleGroup: String,
    val taskGroupType: TaskGroupType,
    val taskInfo: List<TaskInfo> = listOf()
) {
    fun taskCountsByStatus(statusTask: StatusTask? = null): Int {
        return if (statusTask != null) {
            taskInfo.count { it.statusTask == statusTask }
        } else {
            taskInfo.count()
        }
    }

    var progressNumber: Int = 0
}