package com.ctp.taskmanageapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.common.utils.validateTaskInfo
import com.ctp.taskmanageapp.domain.models.SnackBarType
import com.ctp.taskmanageapp.domain.models.filters.StatusTask
import com.ctp.taskmanageapp.domain.models.Type
import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo
import com.ctp.taskmanageapp.domain.usecase.TaskInfoUseCases
import com.ctp.taskmanageapp.presentation.base.BaseViewModel
import com.ctp.taskmanageapp.widget.components.snackbar.SnackBarController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val taskUseCases: TaskInfoUseCases?
) : BaseViewModel() {
    private val _showBottomBar = MutableStateFlow(true)
    val showBottomBar = _showBottomBar.asStateFlow()
    private var taskInfoAll: List<TaskInfo> = listOf()

    fun initUI() {
        viewModelScope.launch {
            getAllTasks {}
        }
    }

    private suspend fun getAllTasks(onSuccess: () -> Unit) {
        taskUseCases?.getAllTasks()?.collect { tasks ->
            taskInfoAll = tasks
            onSuccess()
        }
    }

    fun toggleBottomBar(isVisible: Boolean) {
        _showBottomBar.value = isVisible
    }

    fun getTaskInfoData(
        statusFilter: StatusTask = StatusTask.ALL,
        filterDate: LocalDate = LocalDate.now()
    ): List<TaskInfo> {
        return taskInfoAll.filter {
            (it.statusTask == statusFilter
                    || statusFilter == StatusTask.ALL)
                    && it.startTime.toLocalDate() == filterDate
        }
    }

    fun createTaskInfo(taskInfo: TaskInfo, onSuccess: () -> Unit) {
        val (isValid, errorMessage) = validateTaskInfo(taskInfo)
        if (isValid) {
            viewModelScope.launch {
                taskUseCases?.insertTask(taskInfo)
                SnackBarController.showSnackBar(
                    SnackBarType(
                        R.string.add_task_success_message,
                        Type.SUCCESS
                    )
                )
                onSuccess()
            }
        } else {
            SnackBarController.showSnackBar(SnackBarType(errorMessage, Type.ERROR))
        }
    }

    fun onNextStatusTask(taskInfo: TaskInfo, onSuccess: () -> Unit) {
        val status = when (taskInfo.statusTask) {
            StatusTask.TODO -> Pair(true, StatusTask.IN_PROGRESS)
            StatusTask.IN_PROGRESS -> Pair(true, StatusTask.DONE)
            StatusTask.DONE -> Pair(false, StatusTask.DONE)
            else -> Pair(false, StatusTask.TODO)
        }
        if (status.first) {
            updateStatus(taskInfo, status = status.second, onSuccess)
        }
    }

    private fun updateStatus(
        taskInfo: TaskInfo,
        status: StatusTask = StatusTask.IN_PROGRESS,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            taskUseCases?.updateTask(taskInfo.copy(statusTask = status))
            getAllTasks {
                SnackBarController.showSnackBar(
                    SnackBarType(
                        R.string.calendar_change_status_success,
                        Type.SUCCESS
                    )
                )
                onSuccess.invoke()
            }
        }
    }
}
