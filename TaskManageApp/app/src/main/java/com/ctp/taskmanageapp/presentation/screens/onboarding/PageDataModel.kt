package com.ctp.taskmanageapp.presentation.screens.onboarding

import com.ctp.taskmanageapp.R

data class PageData(
    val backgroundImage: Int? = null,
    val imageContent: Int? = null,
    val titlePage: Int? = null,
    val contentDescription: Int? = null,
    val titleButton: Int? = null,
    val pageIndex: Int = -1
) {
    val onBoardingPage: List<PageData>
        get() = listOf(
            PageData(
                backgroundImage = null,
                imageContent = R.drawable.on_boarding_first_bg,
                titlePage = R.string.onboarding_first_title,
                contentDescription = R.string.onboarding_first_description,
                titleButton = R.string.onboarding_first_button_title,
                pageIndex = 1
            ), PageData(
                backgroundImage = null,
                imageContent = R.drawable.on_boarding_first_bg,
                titlePage = R.string.onboarding_Second_title,
                contentDescription = R.string.onboarding_Second_description,
                titleButton = R.string.onboarding_Second_button_title,
                pageIndex = 1
            )
        )
}
