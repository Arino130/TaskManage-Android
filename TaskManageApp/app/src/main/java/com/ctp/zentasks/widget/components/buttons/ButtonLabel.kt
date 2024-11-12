package com.ctp.zentasks.widget.components.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.ctp.zentasks.R
import com.ctp.zentasks.presentation.common.h3TextStyle
import com.ctp.zentasks.presentation.extensions.getColorFromResources

@Composable
fun ButtonLabel(titleId: Int?, titleStr: String = "", onClick: () -> Unit) {
    val context = LocalContext.current
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            modifier = Modifier.clickable { onClick() },
            text = titleId?.let { context.getString(it) } ?: titleStr,
            style = h3TextStyle,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = context.getColorFromResources(R.color.text_error_color)
        )
    }
}
