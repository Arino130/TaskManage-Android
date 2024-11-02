package com.ctp.taskmanageapp.domain.usecase.taskinfo

import com.ctp.taskmanageapp.data.repositories.TaskInfoRepository
import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo

class DeleteTaskUseCase(private val repository: TaskInfoRepository) {
    suspend operator fun invoke(task: TaskInfo) {
        repository.deleteTask(task)
    }
}