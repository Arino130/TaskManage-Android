package com.ctp.taskmanageapp.data.local.dbstore

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ctp.taskmanageapp.data.local.dao.TaskInfoDao
import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo

@Database(entities = [TaskInfo::class], version = 1)
abstract class TaskInfoDatabase : RoomDatabase() {
    abstract fun taskInfoDao(): TaskInfoDao
}
