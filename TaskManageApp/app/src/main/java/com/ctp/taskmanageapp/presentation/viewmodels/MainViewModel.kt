package com.ctp.taskmanageapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.common.utils.SharedKeys.HAS_DONE_ONBOARDING
import com.ctp.taskmanageapp.common.utils.validateTaskInfo
import com.ctp.taskmanageapp.domain.models.SnackBarType
import com.ctp.taskmanageapp.domain.models.taskgroups.TaskGroup
import com.ctp.taskmanageapp.domain.models.filters.StatusTask
import com.ctp.taskmanageapp.domain.models.Type
import com.ctp.taskmanageapp.domain.models.taskgroups.TaskGroupType
import com.ctp.taskmanageapp.domain.models.tasks.TaskInfo
import com.ctp.taskmanageapp.domain.usecase.SharedUseCases
import com.ctp.taskmanageapp.domain.usecase.TaskCalculationsUseCases
import com.ctp.taskmanageapp.domain.usecase.TaskInfoUseCases
import com.ctp.taskmanageapp.presentation.base.BaseViewModel
import com.ctp.taskmanageapp.widget.components.buttons.model.SegmentModel
import com.ctp.taskmanageapp.widget.components.snackbar.SnackBarController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val taskUseCases: TaskInfoUseCases?,
    private val taskCalcUseCases: TaskCalculationsUseCases?,
    private val sharedUseCases: SharedUseCases?
) : BaseViewModel() {
    private val _showBottomBar = MutableStateFlow(true)
    val showBottomBar = _showBottomBar.asStateFlow()
    private var taskInfoAll: List<TaskInfo> = listOf()
    var filterStatusLatest: SegmentModel? = null
    var filterDatetimeLatest: LocalDate? = null
    var filterGroupTypeLatest: TaskGroupType? = null

    fun initUI() {
        viewModelScope.launch {
            getAllTasks(true) {}
        }
    }

    fun onFinishedOnBoarding() {
        sharedUseCases?.saveBoolean(HAS_DONE_ONBOARDING, true)
    }

    fun shouldSkipOnboarding(): Boolean = sharedUseCases?.getBoolean(
        HAS_DONE_ONBOARDING, false
    ) ?: false

    private suspend fun getAllTasks(isLiveDataChange: Boolean = false, onSuccess: () -> Unit) {
        var isReloadData = !isLiveDataChange
        taskUseCases?.getAllTasks()?.collect { tasks ->
            if (isReloadData) {
                taskInfoAll = tasks.sortedBy { it.startTime }
                isReloadData = false
                onSuccess()
            } else if (isLiveDataChange) {
                taskInfoAll = tasks.sortedBy { it.startTime }
                onSuccess()
            }
        }
    }

    fun toggleBottomBar(isVisible: Boolean) {
        _showBottomBar.value = isVisible
    }

    fun getTaskInfoData(
        statusFilter: StatusTask? = null,
        filterDate: LocalDate? = null,
        groupType: TaskGroupType? = null
    ): List<TaskInfo> {
        return taskInfoAll.filter {
            (statusFilter == null || statusFilter == StatusTask.ALL
                    || it.statusTask == statusFilter) &&
                    (filterDate == null || it.startTime.toLocalDate() == filterDate) &&
                    (groupType == null || it.taskGroupType == groupType)
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

    fun updateStatus(
        taskInfo: TaskInfo,
        status: StatusTask = StatusTask.IN_PROGRESS,
        onSuccess: () -> Unit
    ) {
        val (isValid, errorMessage) = validateTaskInfo(taskInfo)
        if (isValid) {
            viewModelScope.launch {
                taskUseCases?.updateTask(taskInfo.copy(statusTask = status))
                getAllTasks {
                    SnackBarController.showSnackBar(
                        SnackBarType(
                            R.string.change_status_task_success,
                            Type.SUCCESS
                        )
                    )
                    onSuccess.invoke()
                }
            }
        } else {
            SnackBarController.showSnackBar(SnackBarType(errorMessage, Type.ERROR))
        }
    }

    fun getTaskById(taskId: Int, onSuccess: (TaskInfo) -> Unit) {
        viewModelScope.launch {
            taskUseCases?.getTaskById(taskId)?.let {
                onSuccess(it)
            }
        }
    }

    fun removeTaskInfo(
        taskInfo: TaskInfo,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            taskUseCases?.deleteTask(taskInfo)
            getAllTasks {
                SnackBarController.showSnackBar(
                    SnackBarType(
                        R.string.remove_task_success,
                        Type.SUCCESS
                    )
                )
                onSuccess.invoke()
            }
        }
    }

    fun percentCompleteTaskToday(): Int {
        return taskCalcUseCases?.percentCompleteTaskToday(taskInfoAll) ?: 0
    }

    fun getPercentGroupStatus(status: StatusTask): List<TaskGroup> {
        return taskInfoAll.groupBy { it.taskGroupType }.map { item ->
            TaskGroup(
                titleGroup = item.value.firstOrNull()?.titleTask ?: "",
                taskGroupType = item.key,
                taskInfo = item.value
            ).apply {
                progressNumber =
                    taskCalcUseCases?.percentStatusTaskByGroup(item.value, item.key, status) ?: 0
            }
        }
    }

    fun clearDataCalendarScreen() {
        filterStatusLatest = null
        filterDatetimeLatest = null
    }

    fun resetAllData() {
        viewModelScope.launch {
            taskUseCases?.deleteAllTasks()
            getAllTasks {
                SnackBarController.showSnackBar(
                    SnackBarType(
                        R.string.reset_all_data_success,
                        Type.SUCCESS
                    )
                )
            }
        }
    }
}
