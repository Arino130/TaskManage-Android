package com.ctp.zentasks.data.local.converters

import androidx.room.TypeConverter
import com.ctp.zentasks.domain.models.filters.StatusTask

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