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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.presentation.screens.onboarding.component.OnBoardingFinishPageComponent
import com.ctp.taskmanageapp.presentation.screens.onboarding.component.OnBoardingPageComponent
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel

@ExperimentalAnimationApi
@Composable
fun OnBoardingScreen(mainViewModel: MainViewModel, onAddTask: () -> Unit) {
    LaunchedEffect(Unit) {
        mainViewModel.toggleBottomBar(false)
    }
    val currentPage = remember { mutableStateOf(0) }
    val durationMillis = 400
    val pages = remember {
        PageData().onBoardingPage
    }
    AnimatedContent(
        targetState = currentPage.value,
        modifier = Modifier.fillMaxSize(), label = "",
        transitionSpec = {
            slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(
                animationSpec = tween(
                    durationMillis = durationMillis
                )
            ) with
                    slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(
                animationSpec = tween(
                    durationMillis = durationMillis
                )
            )
        }
    ) { targetState ->
        if (targetState < pages.size) {
            OnBoardingPageComponent(pages[targetState]) {
                currentPage.value += 1
            }
        } else {
            OnBoardingFinishPageComponent {
                onAddTask()
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun OnBoardingScreenReview() {
    OnBoardingScreen(MainViewModel(null, null, null)) {}
}