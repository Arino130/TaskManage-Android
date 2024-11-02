package com.ctp.taskmanageapp.domain.usecase.taskinfo

import com.ctp.taskmanageapp.data.repositories.TaskInfoRepository

class DeleteAllTasksUseCase(private val repository: TaskInfoRepository) {
    suspend operator fun invoke() {
        repository.deleteAllTasks()
    }
}