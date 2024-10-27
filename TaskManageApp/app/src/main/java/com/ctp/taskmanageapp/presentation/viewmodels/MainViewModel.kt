package com.ctp.taskmanageapp.presentation.viewmodels

import com.ctp.taskmanageapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {
    private val _showBottomBar = MutableStateFlow(true)
    val showBottomBar = _showBottomBar.asStateFlow()

    fun toggleBottomBar(isVisible: Boolean) {
        _showBottomBar.value = isVisible
    }
}
