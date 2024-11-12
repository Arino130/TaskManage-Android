package com.ctp.zentasks.domain.repository

import com.ctp.zentasks.domain.models.tasks.TaskInfo
import kotlinx.coroutines.flow.Flow

interface ITaskInfoRepository {
    suspend fun insertTask(task: TaskInfo)
    suspend fun deleteTask(task: TaskInfo)
    suspend fun updateTask(task: TaskInfo)
    suspend fun getTaskById(id: Int): TaskInfo
    fun getAllTasks(): Flow<List<TaskInfo>>
    fun getTasksByStartDate(startDate: String): Flow<List<TaskInfo>>
    fun getTasksByEndDate(endDate: String): Flow<List<TaskInfo>>
    suspend fun deleteAllTasks()
}