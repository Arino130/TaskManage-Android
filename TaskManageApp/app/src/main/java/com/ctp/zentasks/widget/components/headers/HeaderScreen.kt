package com.ctp.zentasks.widget.components.headers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.ctp.zentasks.presentation.common.SPACE_BODY_WITH_HEADER_SIZE
import com.ctp.zentasks.presentation.common.h2TextStyle

@Composable
fun HeaderScreen(titleId: Int? = null, titleStr: String = "") {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(bottom = SPACE_BODY_WITH_HEADER_SIZE),
            text = titleId?.let { context.getString(it) } ?: titleStr,
            style = h2TextStyle,
            fontWeight = FontWeight.Bold
        )
    }
}