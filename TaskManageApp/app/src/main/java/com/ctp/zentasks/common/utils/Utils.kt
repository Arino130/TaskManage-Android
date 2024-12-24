package com.ctp.zentasks.common.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.ctp.zentasks.R

fun openUrl(context: Context, urlString: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
    context.startActivity(intent)
}

fun shareApp(context: Context) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    var shareMessage = context.getString(R.string.tag_line)
    shareMessage += Constants.PLAY_STORE_BASE_URL + "\n\n"
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name))
    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
    context.startActivity(Intent.createChooser(shareIntent, "Share This App"))
}
