package com.ctp.taskmanageapp.domain.usecase.taskinfo

import com.ctp.taskmanageapp.data.repositories.TaskInfoRepository
import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo
import kotlinx.coroutines.flow.Flow

class GetTasksByEndDateUseCase(private val repository: TaskInfoRepository) {
    operator fun invoke(endDate: String): Flow<List<TaskInfo>> {
        return repository.getTasksByEndDate(endDate)
    }
}