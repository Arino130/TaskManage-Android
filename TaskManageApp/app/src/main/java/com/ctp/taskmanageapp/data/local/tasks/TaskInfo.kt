package com.ctp.taskmanageapp.data.local.tasks

import com.ctp.taskmanageapp.common.extensions.getFormat12Hour
import com.ctp.taskmanageapp.common.extensions.getFormatDate
import com.ctp.taskmanageapp.data.local.filters.StatusTask
import com.ctp.taskmanageapp.data.local.taskgroups.TaskGroupType
import java.time.LocalDate
import java.time.LocalDateTime

data class TaskInfo(
    val taskGroupType: TaskGroupType,
    val titleTask: String = "",
    val content: String = "",
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val statusTask: StatusTask
) {
    val startTimeFormat: String
        get() {
            return if (LocalDate.now() != startTime.toLocalDate()) {
                startTime.getFormatDate()
            } else {
                startTime.getFormat12Hour()
            }
        }
}
