package com.ctp.taskmanageapp.presentation.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel

@Composable
fun HomeScreen(mainViewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        mainViewModel.toggleBottomBar(true)
    }
    Text(text = "Home")
}

@Preview
@Composable
fun HomeScreenReview() {
    HomeScreen(MainViewModel())
}