package com.ctp.taskmanageapp.data.local.filters

import com.ctp.taskmanageapp.R

enum class StatusFilter(val fullNameId: Int) {
    ALL(R.string.all_type),
    TODO(R.string.todo_type),
    IN_PROGRESS(R.string.in_progress_type),
    DONE(R.string.done_type)
}