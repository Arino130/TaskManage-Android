package com.ctp.taskmanageapp.presentation.base

import android.content.Context
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val enterTransition = 700
    val exitTransition = 700

    fun buildVersionName(context: Context) =
        context.packageManager.getPackageInfo(context.packageName, 0).versionName

    fun buildVersionCode(context: Context) =
        context.packageManager.getPackageInfo(context.packageName, 0).versionCode
}
