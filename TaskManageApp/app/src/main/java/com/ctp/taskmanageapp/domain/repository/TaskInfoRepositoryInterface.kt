package com.ctp.taskmanageapp.domain.repository

import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo
import kotlinx.coroutines.flow.Flow

interface TaskInfoRepositoryInterface {
    suspend fun insertTask(task: TaskInfo)
    suspend fun deleteTask(task: TaskInfo)
    suspend fun updateTask(task: TaskInfo)
    suspend fun getTaskById(id: Int): TaskInfo
    fun getAllTasks(): Flow<List<TaskInfo>>
    fun getTasksByStartDate(startDate: String): Flow<List<TaskInfo>>
    fun getTasksByEndDate(endDate: String): Flow<List<TaskInfo>>
    suspend fun deleteAllTasks()
}