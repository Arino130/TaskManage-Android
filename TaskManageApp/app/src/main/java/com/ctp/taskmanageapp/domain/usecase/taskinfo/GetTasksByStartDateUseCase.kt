package com.ctp.taskmanageapp.domain.usecase.taskinfo

import com.ctp.taskmanageapp.data.repositories.TaskInfoRepository
import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo
import kotlinx.coroutines.flow.Flow

class GetTasksByStartDateUseCase(private val repository: TaskInfoRepository) {
    operator fun invoke(startDate: String): Flow<List<TaskInfo>> {
        return repository.getTasksByStartDate(startDate)
    }
}