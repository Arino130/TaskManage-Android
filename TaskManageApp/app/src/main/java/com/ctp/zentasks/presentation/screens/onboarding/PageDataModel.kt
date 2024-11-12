package com.ctp.zentasks.presentation.screens.onboarding

import com.ctp.zentasks.R

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
                imageContent = R.drawable.on_boarding_first_img,
                titlePage = R.string.onboarding_first_title,
                contentDescription = R.string.onboarding_first_description,
                titleButton = R.string.onboarding_first_button_title,
                pageIndex = 1
            ), PageData(
                backgroundImage = null,
                imageContent = R.drawable.on_boarding_second_img,
                titlePage = R.string.onboarding_second_title,
                contentDescription = R.string.onboarding_second_description,
                titleButton = R.string.onboarding_second_button_title,
                pageIndex = 2
            ), PageData(
                backgroundImage = null,
                imageContent = R.drawable.on_boarding_third_img,
                titlePage = R.string.onboarding_third_title,
                contentDescription = R.string.onboarding_third_description,
                titleButton = R.string.onboarding_third_button_title,
                pageIndex = 3
            )
        )
}
