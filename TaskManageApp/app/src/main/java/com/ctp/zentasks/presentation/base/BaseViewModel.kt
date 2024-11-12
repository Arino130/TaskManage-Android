package com.ctp.zentasks.presentation.base

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ctp.zentasks.common.utils.Constants
import com.ctp.zentasks.common.utils.openUrl
import com.ctp.zentasks.common.utils.shareApp

abstract class BaseViewModel : ViewModel() {
    val enterTransition = 700
    val exitTransition = 700

    fun buildVersionName(context: Context): String =
        context.packageManager.getPackageInfo(context.packageName, 0).versionName

    fun buildVersionCode(context: Context) =
        context.packageManager.getPackageInfo(context.packageName, 0).versionCode

    fun rateUs(context: Context) {
        val appUrl = Constants.PLAY_STORE_BASE_URL + context.packageName
        openUrl(context, appUrl)
    }

    fun shareMyApp(context: Context) {
        shareApp(context)
    }

    fun discoverMore(context: Context) {
        val appUrl = Constants.MY_STORE_BASE_URL
        openUrl(context, appUrl)
    }
}
