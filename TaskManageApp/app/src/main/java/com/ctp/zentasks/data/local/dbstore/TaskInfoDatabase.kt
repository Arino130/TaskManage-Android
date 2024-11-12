package com.ctp.zentasks.data.local.dbstore

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ctp.zentasks.data.local.dao.TaskInfoDao
import com.ctp.zentasks.domain.models.tasks.TaskInfo

@Database(entities = [TaskInfo::class], version = 1)
abstract class TaskInfoDatabase : RoomDatabase() {
    abstract fun taskInfoDao(): TaskInfoDao
}
