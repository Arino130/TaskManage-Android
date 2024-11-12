package com.ctp.zentasks.widget.components.headers

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.zentasks.R
import com.ctp.zentasks.presentation.common.ICON_BACK_SCREEN_SIZE
import com.ctp.zentasks.presentation.common.SPACE_BODY_WITH_HEADER_SIZE
import com.ctp.zentasks.presentation.common.h2TextStyle

@Composable
fun HeaderSubScreen(
    titleId: Int? = null,
    titleStr: String = "",
    actionButtonIcon: Int? = null,
    actionButton: () -> Unit = {},
    onBackButtonIcon: Int? = R.drawable.ic_arrow_back,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = SPACE_BODY_WITH_HEADER_SIZE),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBackButtonIcon != null) {
            Image(
                modifier = Modifier
                    .size(ICON_BACK_SCREEN_SIZE)
                    .clickable { onBack() },
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )
        }

        Text(
            modifier = Modifier
                .weight(1f),
            text = titleId?.let { context.getString(it) } ?: titleStr,
            style = h2TextStyle,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        if (actionButtonIcon != null) {
            Image(
                modifier = Modifier
                    .size(ICON_BACK_SCREEN_SIZE)
                    .clickable { actionButton() },
                painter = painterResource(id = actionButtonIcon),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )
        } else {
            Spacer(modifier = Modifier.size(ICON_BACK_SCREEN_SIZE))
        }
    }
}

@Preview
@Composable
fun HeaderSubScreenPreview() {
    Column {
        HeaderSubScreen(
            titleStr = "Sub Screen"
        ) {

        }
        Spacer(modifier = Modifier.size(SPACE_BODY_WITH_HEADER_SIZE))
        HeaderSubScreen(
            titleStr = "Sub Screen",
            actionButtonIcon = R.drawable.ic_calendar
        ) {

        }
    }
}