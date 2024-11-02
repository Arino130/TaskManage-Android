package com.ctp.taskmanageapp.domain.models.filters

import com.ctp.taskmanageapp.R

enum class StatusTask(val fullNameId: Int, val labelColor: Int, val backgroundColor: Int) {
    ALL(R.string.all_type, R.color.status_todo_label, R.color.status_todo_label_background),
    TODO(R.string.todo_type, R.color.status_todo_label, R.color.status_todo_label_background),
    IN_PROGRESS(
        R.string.in_progress_type, R.color.status_in_progress_label,
        R.color.status_in_progress_label_background
    ),
    DONE(R.string.done_type, R.color.status_done_label, R.color.status_done_label_background)
}