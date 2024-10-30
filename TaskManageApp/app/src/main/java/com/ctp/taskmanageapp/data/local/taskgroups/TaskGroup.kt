package com.ctp.taskmanageapp.data.local.taskgroups

data class TaskGroup(
    val titleGroup: String,
    val taskGroupType: TaskGroupType
) {
    val taskCounts: Int
        get () = 10 // TODO: MOCK DATA
    val progressNumber: Int
        get () = 80 // TODO: MOCK DATA
}