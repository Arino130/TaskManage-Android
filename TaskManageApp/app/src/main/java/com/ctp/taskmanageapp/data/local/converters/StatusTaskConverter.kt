package com.ctp.taskmanageapp.data.local.converters

import androidx.room.TypeConverter
import com.ctp.taskmanageapp.domain.models.filters.StatusTask

object StatusTaskConverter {
    @TypeConverter
    fun fromStatusTask(statusTask: StatusTask): String {
        return statusTask.name
    }

    @TypeConverter
    fun toStatusTask(value: String): StatusTask {
        return StatusTask.valueOf(value)
    }
}