package com.ctp.taskmanageapp.data.repositories

import com.ctp.taskmanageapp.data.local.dao.TaskDao
import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo
import kotlinx.coroutines.flow.Flow

class TaskInfoRepository(private val taskDao: TaskDao) {

    suspend fun insertTask(task: TaskInfo) = taskDao.insertTask(task)

    suspend fun deleteTask(task: TaskInfo) = taskDao.deleteTask(task)

    suspend fun updateTask(task: TaskInfo) = taskDao.updateTask(task)

    suspend fun getTaskById(id: Int): TaskInfo = taskDao.getTaskById(id)

    fun getAllTasks(): Flow<List<TaskInfo>> = taskDao.getAllTasks()

    fun getTasksByStartDate(startDate: String): Flow<List<TaskInfo>> = taskDao.getTasksByStartDate(startDate)

    fun getTasksByEndDate(endDate: String): Flow<List<TaskInfo>> = taskDao.getTasksByEndDate(endDate)

    suspend fun deleteAllTasks() = taskDao.deleteAllTasks()
}