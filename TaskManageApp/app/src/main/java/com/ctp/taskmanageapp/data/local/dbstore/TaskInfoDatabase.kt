package com.ctp.taskmanageapp.data.local.dbstore

import android.app.TaskInfo
import androidx.room.Database
import androidx.room.RoomDatabase
import com.ctp.taskmanageapp.data.local.dao.TaskDao

@Database(
    entities = [TaskInfo::class],
    version = 2,
)
abstract class TaskInfoDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}