package com.ctp.taskmanageapp.widget.components.headers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.presentation.common.ICON_BACK_SCREEN_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_BODY_WITH_HEADER_SIZE
import com.ctp.taskmanageapp.presentation.common.h2TextStyle

@Composable
fun HeaderSubScreen(titleId: Int? = null, titleStr: String = "", onBack: () -> Unit) {
    val context = LocalContext.current
    Row {
        Image(
            modifier = Modifier
                .size(ICON_BACK_SCREEN_SIZE),
            painter = painterResource(
                id = R.drawable.ic_arrow_back
            ),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )
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
}

@Preview
@Composable
fun HeaderSubScreenPreview() {
    HeaderSubScreen(titleStr = "Sub Screen") {

    }
}