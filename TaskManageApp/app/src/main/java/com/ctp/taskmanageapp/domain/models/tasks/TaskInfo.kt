package com.ctp.taskmanageapp.domain.models.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ctp.taskmanageapp.common.extensions.getFormat12Hour
import com.ctp.taskmanageapp.common.extensions.getFormatDate
import com.ctp.taskmanageapp.domain.models.filters.StatusTask
import com.ctp.taskmanageapp.domain.models.TaskGroupType
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = "tasks_info_table")
data class TaskInfo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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
