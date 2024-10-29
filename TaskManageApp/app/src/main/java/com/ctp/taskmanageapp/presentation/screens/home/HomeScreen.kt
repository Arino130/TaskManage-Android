package com.ctp.taskmanageapp.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ctp.taskmanageapp.presentation.screens.home.component.CardOverviewTask
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel

@Composable
fun HomeScreen(mainViewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        mainViewModel.toggleBottomBar(true)
    }
    Box(modifier = Modifier.padding(15.dp)) {
       CardOverviewTask()
    }
}

@Preview
@Composable
fun HomeScreenReview() {
    HomeScreen(MainViewModel())
}