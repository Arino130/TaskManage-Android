package com.ctp.taskmanageapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskInfo)

    @Delete
    suspend fun deleteTask(task: TaskInfo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(task: TaskInfo)

    @Query("SELECT * FROM task_info_table WHERE id=:id")
    suspend fun getTaskById(id: Int): TaskInfo

    @Query("SELECT * FROM task_info_table")
    fun getAllTasks(): Flow<List<TaskInfo>>

    @Query("SELECT * FROM task_info_table WHERE DATE(startTime) = :startDate")
    fun getTasksByStartDate(startDate: String): Flow<List<TaskInfo>>

    @Query("SELECT * FROM task_info_table WHERE DATE(endTime) = :endDate")
    fun getTasksByEndDate(endDate: String): Flow<List<TaskInfo>>

    @Query("DELETE FROM task_info_table")
    suspend fun deleteAllTasks()

}