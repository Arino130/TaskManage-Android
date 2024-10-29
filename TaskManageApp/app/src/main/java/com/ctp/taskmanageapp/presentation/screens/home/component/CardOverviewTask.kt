package com.ctp.taskmanageapp.presentation.screens.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.presentation.common.h3TextStyle
import com.ctp.taskmanageapp.presentation.extensions.getColorFromResources
import com.ctp.taskmanageapp.widget.components.ButtonTMComponent
import com.ctp.taskmanageapp.widget.components.ButtonType
import com.ctp.taskmanageapp.widget.components.reports.CircularProgressBar

@Composable
fun CardOverviewTask() {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = context.getColorFromResources(
                R.color.button_background_primary
            )
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = PaddingValues(vertical = 15.dp))
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(paddingValues = PaddingValues(horizontal = 15.dp)),
            ) {
                Text(
                    text = context.getString(R.string.home_overview_card_title),
                    style = h3TextStyle,
                    color = context.getColorFromResources(R.color.white)
                )
                Box(
                    modifier = Modifier
                        .width(130.dp)
                        .height(70.dp)
                        .padding(paddingValues = PaddingValues(top = 20.dp, bottom = 14.dp))
                ) {
                    ButtonTMComponent(
                        titleButton = context.getString(R.string.home_view_task_button),
                        buttonType = ButtonType.Normal,
                    ) {
                        // TODO: OnClick View Task
                    }
                }
            }
            Box(modifier = Modifier.padding(paddingValues = PaddingValues(top = 10.dp))) {
                CircularProgressBar(
                    modifier = Modifier
                        .size(250.dp)
                        .padding(paddingValues = PaddingValues(top = 20.dp)),
                    initialValue = 50,
                    size = 80.dp,
                    primaryColor = context.getColorFromResources(R.color.CircularProgressBarPrimary),
                    secondaryColor = context.getColorFromResources(R.color.CircularProgressBarSecondary),
                    onPositionChange = {

                    }
                )
            }
            Image(
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp),
                painter = painterResource(id = R.drawable.ic_button_three_dot),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Preview
@Composable
fun CardOverviewTaskReview() {
    CardOverviewTask()
}