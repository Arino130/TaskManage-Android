package com.ctp.taskmanageapp.domain.models

import com.ctp.taskmanageapp.R

enum class TaskGroupType(
    val typeTitleId: Int,
    val typeIcon: Int,
    val background: Int,
    val progressLineColor: Int,
    val progressCircularColor: Int,
    val progressCircularSecondaryColor: Int
) {
    OfficeProject(
        typeTitleId = R.string.type_group_office,
        typeIcon = R.drawable.ic_group_offical,
        background = R.color.group_office_background,
        progressLineColor = R.color.group_office_line_progress,
        progressCircularColor = R.color.group_office_circular_progress,
        progressCircularSecondaryColor = R.color.group_office_circular_secondary
    ),
    PersonalProject(
        typeTitleId = R.string.type_group_personal,
        typeIcon = R.drawable.ic_group_personal,
        background = R.color.group_personal_background,
        progressLineColor = R.color.group_personal_line_progress,
        progressCircularColor = R.color.group_personal_circular_progress,
        progressCircularSecondaryColor = R.color.group_personal_circular_secondary
    )
}