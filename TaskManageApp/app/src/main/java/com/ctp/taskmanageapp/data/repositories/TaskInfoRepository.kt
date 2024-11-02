package com.ctp.taskmanageapp.data.repositories

import com.ctp.taskmanageapp.data.local.dao.TaskInfoDao
import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo
import com.ctp.taskmanageapp.domain.repository.TaskInfoRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskInfoRepository @Inject constructor(
    private val taskDao: TaskInfoDao
) : TaskInfoRepositoryInterface {

    override suspend fun insertTask(task: TaskInfo) = taskDao.insertTask(task)

    override suspend fun deleteTask(task: TaskInfo) = taskDao.deleteTask(task)

    override suspend fun updateTask(task: TaskInfo) = taskDao.updateTask(task)

    override suspend fun getTaskById(id: Int): TaskInfo = taskDao.getTaskById(id)

    override fun getAllTasks(): Flow<List<TaskInfo>> = taskDao.getAllTasks()

    override fun getTasksByStartDate(startDate: String): Flow<List<TaskInfo>> =
        taskDao.getTasksByStartDate(startDate)

    override fun getTasksByEndDate(endDate: String): Flow<List<TaskInfo>> =
        taskDao.getTasksByEndDate(endDate)

    override suspend fun deleteAllTasks() = taskDao.deleteAllTasks()
}
