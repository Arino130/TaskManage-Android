package com.ctp.taskmanageapp.domain.usecase

import com.ctp.taskmanageapp.domain.models.TaskGroupType
import com.ctp.taskmanageapp.domain.models.filters.StatusTask
import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo
import java.time.LocalDate
import javax.inject.Inject

class TaskCalculationsUseCases @Inject constructor() {
    fun percentCompleteTaskToday(taskInfoAll: List<TaskInfo>): Int {
        val (completedTasks, incompleteTasks) = taskInfoAll
            .filter { it.startTime.toLocalDate() == LocalDate.now() }
            .partition { it.isTaskDone }

        val totalTasksToday = completedTasks.size + incompleteTasks.size
        if (totalTasksToday == 0) return 0

        return (completedTasks.size * 100) / totalTasksToday
    }

    fun percentStatusTaskByGroup(
        taskInfoAll: List<TaskInfo>,
        taskGroupType: TaskGroupType,
        statusTask: StatusTask
    ): Int {
        val (completedTasks, incompleteTasks) = taskInfoAll
            .filter { it.taskGroupType == taskGroupType }
            .partition { it.statusTask == statusTask }

        val totalTasksToday = completedTasks.size + incompleteTasks.size
        if (totalTasksToday == 0) return 0

        return (completedTasks.size * 100) / totalTasksToday
    }
}