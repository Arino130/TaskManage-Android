package com.ctp.taskmanageapp.widget.components.headers

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.presentation.common.ICON_BACK_SCREEN_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_BODY_WITH_HEADER_SIZE
import com.ctp.taskmanageapp.presentation.common.h2TextStyle

@Composable
fun HeaderSubScreen(titleId: Int? = null, titleStr: String = "", onBack: () -> Unit) {
    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .size(ICON_BACK_SCREEN_SIZE)
                .clickable {
                    onBack()
                },
            painter = painterResource(
                id = R.drawable.ic_arrow_back
            ),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier
                .padding(
                    bottom = SPACE_BODY_WITH_HEADER_SIZE, end = ICON_BACK_SCREEN_SIZE
                )
                .fillMaxWidth(),
            text = titleId?.let { context.getString(it) } ?: titleStr,
            style = h2TextStyle,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun HeaderSubScreenPreview() {
    HeaderSubScreen(titleStr = "Sub Screen") {

    }
}