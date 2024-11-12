package com.ctp.zentasks.domain.models.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ctp.zentasks.common.extensions.getFormat12Hour
import com.ctp.zentasks.common.extensions.getFormatDate
import com.ctp.zentasks.data.local.converters.LocalDateTimeConverter
import com.ctp.zentasks.data.local.converters.StatusTaskConverter
import com.ctp.zentasks.data.local.converters.TaskGroupTypeConverter
import com.ctp.zentasks.domain.models.filters.StatusTask
import com.ctp.zentasks.domain.models.taskgroups.TaskGroupType
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = "task_info_table")
@TypeConverters(
    TaskGroupTypeConverter::class,
    LocalDateTimeConverter::class,
    StatusTaskConverter::class
)
data class TaskInfo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val taskGroupType: TaskGroupType = TaskGroupType.OfficeProject,
    val titleTask: String = "",
    val content: String = "",
    val startTime: LocalDateTime = LocalDateTime.now(),
    val endTime: LocalDateTime = LocalDateTime.now(),
    val statusTask: StatusTask = StatusTask.TODO
) {
    val startTimeFormat: String
        get() {
            return if (LocalDate.now() != startTime.toLocalDate()) {
                startTime.getFormatDate()
            } else {
                startTime.getFormat12Hour()
            }
        }

    val endTimeFormat: String
        get() {
            return if (LocalDate.now() != endTime.toLocalDate()) {
                endTime.getFormatDate()
            } else {
                endTime.getFormat12Hour()
            }
        }

    val isTaskDone: Boolean
        get() = statusTask == StatusTask.DONE
}
