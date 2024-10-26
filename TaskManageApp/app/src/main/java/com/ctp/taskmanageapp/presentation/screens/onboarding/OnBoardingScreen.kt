package com.ctp.taskmanageapp.presentation.screens.onboarding

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.presentation.screens.onboarding.component.OnBoardingPageComponent
import com.ctp.taskmanageapp.presentation.screens.onboarding.component.PageData

@ExperimentalAnimationApi
@Composable
fun OnBoardingScreen() {
    val context = LocalContext.current
    val pages = listOf(
        PageData(
            backgroundImage = R.drawable.background_onboarding,
            imageContent = R.drawable.ic_launcher_background,
            titlePage = R.string.onboarding_first_title,
            contentDescription = R.string.onboarding_first_description,
            titleButton = R.string.onboarding_first_button_title,
            pageIndex = 1
        ),
        PageData(
            backgroundImage = R.drawable.background_onboarding,
            imageContent = R.drawable.ic_launcher_background,
            titlePage = R.string.onboarding_Second_title,
            contentDescription = R.string.onboarding_Second_description,
            titleButton = R.string.onboarding_Second_button_title,
            pageIndex = 1
        )
    )
    val currentPage = remember { mutableStateOf(0) }
    AnimatedContent(
        targetState = currentPage.value,
        modifier = Modifier.fillMaxSize(), label = "",
        transitionSpec = {
            slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 300)) with
                    slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 300))
        }
    ) { targetState ->
        OnBoardingPageComponent(pages[targetState]) {
            if (currentPage.value < pages.size-1) {
                currentPage.value += 1
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun OnBoardingScreenReview() {
    OnBoardingScreen()
}