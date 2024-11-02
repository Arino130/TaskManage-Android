package com.ctp.taskmanageapp.domain.usecase.taskinfo

import com.ctp.taskmanageapp.data.repositories.TaskInfoRepository
import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo
import kotlinx.coroutines.flow.Flow

class GetAllTasksUseCase(private val repository: TaskInfoRepository) {
    operator fun invoke(): Flow<List<TaskInfo>> {
        return repository.getAllTasks()
    }
}