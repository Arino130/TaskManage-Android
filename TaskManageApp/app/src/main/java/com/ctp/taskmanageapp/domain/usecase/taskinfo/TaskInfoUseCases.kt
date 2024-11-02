package com.ctp.taskmanageapp.domain.usecase.taskinfo

data class TaskInfoUseCases (
    val insertTask: InsertTaskUseCase,
    val deleteTask: DeleteTaskUseCase,
    val updateTask: UpdateTaskUseCase,
    val getTaskById: GetTaskByIdUseCase,
    val getAllTasks: GetAllTasksUseCase,
    val getTasksByStartDate: GetTasksByStartDateUseCase,
    val getTasksByEndDate: GetTasksByEndDateUseCase,
    val deleteAllTasks: DeleteAllTasksUseCase
)
