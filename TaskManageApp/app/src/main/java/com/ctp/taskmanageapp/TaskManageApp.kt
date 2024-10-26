package com.ctp.taskmanageapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaskManageApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}