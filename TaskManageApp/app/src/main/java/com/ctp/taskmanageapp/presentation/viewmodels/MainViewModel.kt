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
import com.ctp.taskmanageapp.presentation.common.SnackBarController
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

//    private val taskInfoAll: List<TaskInfo> = listOf(
//        TaskInfo(
//            taskGroupType = TaskGroupType.PersonalProject,
//            titleTask = "Lorem Ipsum is simply dummy text 123456789",
//            content = "Lorem Ipsum is simply dummy text of the printing",
//            startTime = LocalDateTime.now(),
//            endTime = LocalDateTime.now().plusHours(2),
//            statusTask = StatusTask.TODO
//        ),
//        TaskInfo(
//            taskGroupType = TaskGroupType.OfficeProject,
//            titleTask = "Lorem Ipsum is simply dummy text 2",
//            content = "Lorem Ipsum is simply dummy text of the printing",
//            startTime = LocalDateTime.now(),
//            endTime = LocalDateTime.now().plusDays(2),
//            statusTask = StatusTask.IN_PROGRESS
//        ),
//        TaskInfo(
//            taskGroupType = TaskGroupType.OfficeProject,
//            titleTask = "Lorem Ipsum is simply dummy text 7",
//            content = "Lorem Ipsum is simply dummy text of the printing",
//            startTime = LocalDateTime.now(),
//            endTime = LocalDateTime.now().plusDays(2),
//            statusTask = StatusTask.DONE
//        ),
//        TaskInfo(
//            taskGroupType = TaskGroupType.OfficeProject,
//            titleTask = "Lorem Ipsum is simply dummy text 3",
//            content = "Lorem Ipsum is simply dummy text of the printing",
//            startTime = LocalDateTime.now().plusMonths(2),
//            endTime = LocalDateTime.now().plusMonths(2).plusHours(2),
//            statusTask = StatusTask.DONE
//        ),
//        TaskInfo(
//            taskGroupType = TaskGroupType.OfficeProject,
//            titleTask = "Lorem Ipsum is simply dummy text 4",
//            content = "Lorem Ipsum is simply dummy text of the printing",
//            startTime = LocalDateTime.now().plusDays(5),
//            endTime = LocalDateTime.now().plusDays(10),
//            statusTask = StatusTask.IN_PROGRESS
//        ),
//        TaskInfo(
//            taskGroupType = TaskGroupType.OfficeProject,
//            titleTask = "Lorem Ipsum is simply dummy text 5",
//            content = "Lorem Ipsum is simply dummy text of the printing",
//            startTime = LocalDateTime.now().minusDays(5),
//            endTime = LocalDateTime.now().minusDays(1),
//            statusTask = StatusTask.IN_PROGRESS
//        )
//    )

    private var taskInfoAll: List<TaskInfo> = listOf()

    fun initUI() {
        viewModelScope.launch {
            taskUseCases?.getAllTasks()?.collect { tasks ->
                taskInfoAll = tasks
            }
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
                SnackBarController.showSnackBar(SnackBarType(R.string.add_task_success_message, Type.SUCCESS))
                onSuccess()
            }
        } else {
            SnackBarController.showSnackBar(SnackBarType(errorMessage, Type.ERROR))
        }
    }
}
