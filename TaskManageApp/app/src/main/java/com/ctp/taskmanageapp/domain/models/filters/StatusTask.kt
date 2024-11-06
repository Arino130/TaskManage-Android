package com.ctp.taskmanageapp.domain.models.filters

import com.ctp.taskmanageapp.R

enum class StatusTask(
    val fullNameId: Int,
    val labelColor: Int,
    val backgroundColor: Int,
    val iconStatus: Int,
    val isDisplay: Boolean,
    val sortIndex: Int
) {
    ALL(
        R.string.all_type,
        R.color.status_todo_label,
        R.color.status_todo_label_background,
        iconStatus = R.drawable.ic_status_todo,
        isDisplay = false,
        sortIndex = 1
    ),
    TODO(
        R.string.todo_type,
        R.color.status_todo_label,
        R.color.status_todo_label_background,
        iconStatus = R.drawable.ic_status_todo,
        isDisplay = true,
        sortIndex = 2
    ),
    IN_PROGRESS(
        R.string.in_progress_type,
        R.color.status_in_progress_label,
        R.color.status_in_progress_label_background,
        iconStatus = R.drawable.ic_status_progress,
        isDisplay = true,
        sortIndex = 3
    ),
    DONE(
        R.string.done_type,
        R.color.status_done_label,
        R.color.status_done_label_background,
        iconStatus = R.drawable.ic_status_done,
        isDisplay = true,
        sortIndex = 4
    )
}