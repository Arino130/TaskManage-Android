package com.ctp.taskmanageapp.presentation.screens.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.presentation.common.ICON_LARGE_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_20_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.common.h1TitleStyle
import com.ctp.taskmanageapp.presentation.common.h3TextStyle

@Composable
fun OnBoardingFinishPageComponent(onClickAddTask: () -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = context.getString(
                    R.string.onboarding_finish_title,
                    context.getString(R.string.app_name)
                ),
                style = h1TitleStyle,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = context.getString(R.string.onboarding_finish_content),
                style = h3TextStyle,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(top = SPACE_CONTENT_20_SIZE))
            Image(
                modifier = Modifier
                    .size(ICON_LARGE_SIZE)
                    .clickable { onClickAddTask() },
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Preview
@Composable
fun OnBoardingFinishPageComponentReview() {
    OnBoardingFinishPageComponent() {}
}
