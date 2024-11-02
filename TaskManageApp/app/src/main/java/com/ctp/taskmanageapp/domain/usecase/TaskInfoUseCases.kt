package com.ctp.taskmanageapp.domain.usecase

import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo
import com.ctp.taskmanageapp.domain.repository.TaskInfoRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskInfoUseCases @Inject constructor(
    private val taskInfoRepository: TaskInfoRepositoryInterface
) {
    suspend fun insertTask(task: TaskInfo) {
        taskInfoRepository.insertTask(task)
    }

    suspend fun deleteTask(task: TaskInfo) {
        taskInfoRepository.deleteTask(task)
    }

    suspend fun updateTask(task: TaskInfo) {
        taskInfoRepository.updateTask(task)
    }

    suspend fun getTaskById(id: Int): TaskInfo {
        return taskInfoRepository.getTaskById(id)
    }

    fun getAllTasks(): Flow<List<TaskInfo>> {
        return taskInfoRepository.getAllTasks()
    }

    fun getTasksByStartDate(startDate: String): Flow<List<TaskInfo>> {
        return taskInfoRepository.getTasksByStartDate(startDate)
    }

    fun getTasksByEndDate(endDate: String): Flow<List<TaskInfo>> {
        return taskInfoRepository.getTasksByEndDate(endDate)
    }

    suspend fun deleteAllTasks() {
        taskInfoRepository.deleteAllTasks()
    }
}