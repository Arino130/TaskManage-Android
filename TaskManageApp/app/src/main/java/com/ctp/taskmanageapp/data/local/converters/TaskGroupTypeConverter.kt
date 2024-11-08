package com.ctp.taskmanageapp.data.local.converters

import androidx.room.TypeConverter
import com.ctp.taskmanageapp.domain.models.taskgroups.TaskGroupType

object TaskGroupTypeConverter {
    @TypeConverter
    fun fromTaskGroupType(taskGroupType: TaskGroupType): String {
        return taskGroupType.name
    }

    @TypeConverter
    fun toTaskGroupType(value: String): TaskGroupType {
        return TaskGroupType.valueOf(value)
    }
}
