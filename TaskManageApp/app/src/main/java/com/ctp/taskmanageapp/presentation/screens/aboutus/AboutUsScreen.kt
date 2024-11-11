package com.ctp.taskmanageapp.presentation.screens.aboutus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_40_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_DEFAULT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_12_SIZE
import com.ctp.taskmanageapp.presentation.common.h2TextLightStyle
import com.ctp.taskmanageapp.presentation.extensions.getColorFromResources
import com.ctp.taskmanageapp.presentation.ui.theme.getColorSchemeBackground
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel
import com.ctp.taskmanageapp.widget.components.headers.HeaderSubScreen

@Composable
fun AboutUsScreen(mainViewModel: MainViewModel, onBack: () -> Unit) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        mainViewModel.toggleBottomBar(false)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(getColorSchemeBackground())
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = SPACE_SMALL_12_SIZE)
                .padding(bottom = SPACE_CONTENT_40_SIZE)
        ) {
            HeaderSubScreen(titleId = R.string.about_us_screen_title) {
                onBack()
            }
            Spacer(modifier = Modifier.padding(top = SPACE_DEFAULT_SIZE))
            Text(
                modifier = Modifier.fillMaxSize(),
                text = context.getString(R.string.about_app_body),
                color = context.getColorFromResources(R.color.text_blank_color),
                style = h2TextLightStyle,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun AboutUsScreenPreview() {
    AboutUsScreen(MainViewModel(null, null, null)) {}
}
