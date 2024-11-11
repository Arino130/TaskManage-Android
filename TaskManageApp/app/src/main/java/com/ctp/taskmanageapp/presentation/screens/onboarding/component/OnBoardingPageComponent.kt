package com.ctp.taskmanageapp.presentation.screens.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import com.ctp.taskmanageapp.presentation.common.ON_BOARDING_IMAGE_CONTENT
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_28_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_DEFAULT_SIZE
import com.ctp.taskmanageapp.presentation.common.h1TitleStyle
import com.ctp.taskmanageapp.presentation.common.h3TextStyle
import com.ctp.taskmanageapp.presentation.screens.onboarding.PageData
import com.ctp.taskmanageapp.widget.components.buttons.ButtonTMComponent
import com.ctp.taskmanageapp.widget.components.buttons.ButtonType

@Composable
fun OnBoardingPageComponent(pageData: PageData, onclickDone: () -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RectangleShape
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                pageData.backgroundImage?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = null,
                        Modifier
                            .fillMaxWidth()
                            .fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                pageData.imageContent?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = null,
                        Modifier.size(ON_BOARDING_IMAGE_CONTENT),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    pageData.titlePage?.let {
                        Text(
                            text = context.getString(it),
                            style = h1TitleStyle
                        )
                    }
                    pageData.contentDescription?.let {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                                .padding(
                                    vertical = SPACE_DEFAULT_SIZE
                                ),
                            text = context.getString(it),
                            style = h3TextStyle,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = SPACE_CONTENT_28_SIZE))
                    pageData.titleButton?.let {
                        Box(
                            modifier = Modifier
                                .padding(SPACE_DEFAULT_SIZE)
                        ) {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                ButtonTMComponent(
                                    titleButton = context.getString(it),
                                    iconButtonR = R.drawable.ic_arrow_right,
                                    buttonType = ButtonType.Primary,
                                ) { onclickDone() }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun OnBoardingPageComponentReview() {
    OnBoardingPageComponent(
        PageData(
            backgroundImage = R.drawable.background_onboarding,
            imageContent = R.drawable.ic_launcher_background,
            titlePage = R.string.onboarding_first_title,
            contentDescription = R.string.onboarding_first_description,
            titleButton = R.string.onboarding_first_button_title,
            pageIndex = 1
        )
    ) {}
}
