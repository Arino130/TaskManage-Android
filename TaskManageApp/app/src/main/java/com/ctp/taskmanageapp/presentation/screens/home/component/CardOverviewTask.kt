package com.ctp.taskmanageapp.presentation.screens.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import com.ctp.taskmanageapp.presentation.common.CIRCULAR_LARGE_SIZE
import com.ctp.taskmanageapp.presentation.common.COLUMN_CONTENT_SIZE
import com.ctp.taskmanageapp.presentation.common.ELEVATION_DEFAULT_SIZE
import com.ctp.taskmanageapp.presentation.common.ICON_SMALL_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_20_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_DEFAULT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_10_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_4_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.common.h3TextStyle
import com.ctp.taskmanageapp.presentation.extensions.getColorFromResources
import com.ctp.taskmanageapp.widget.components.buttons.ButtonTMComponent
import com.ctp.taskmanageapp.widget.components.buttons.ButtonType
import com.ctp.taskmanageapp.widget.components.reports.CircularProgressBar

@Composable
fun CardOverviewTask(
    valuePercent: Int,
    onClickViewTask: () -> Unit,
    onClickActionMore: () -> Unit
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(COLUMN_CONTENT_SIZE)
            .padding(vertical = SPACE_SMALL_8_SIZE),
        elevation = CardDefaults.cardElevation(defaultElevation = ELEVATION_DEFAULT_SIZE),
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
                .padding(vertical = SPACE_DEFAULT_SIZE)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(horizontal = SPACE_SMALL_4_SIZE),
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
                        .padding(
                            paddingValues = PaddingValues(
                                top = SPACE_CONTENT_20_SIZE, bottom = SPACE_SMALL_8_SIZE
                            )
                        )
                ) {
                    ButtonTMComponent(
                        titleButton = context.getString(R.string.home_view_task_button),
                        buttonType = ButtonType.Normal,
                    ) {
                        onClickViewTask()
                    }
                }
            }
            Box(
                modifier = Modifier.padding(
                    paddingValues = PaddingValues(top = SPACE_SMALL_10_SIZE)
                )
            ) {
                CircularProgressBar(
                    initialValue = valuePercent,
                    size = CIRCULAR_LARGE_SIZE,
                    primaryColor =
                    context.getColorFromResources(R.color.CircularProgressBarPrimary),
                    secondaryColor =
                    context.getColorFromResources(R.color.CircularProgressBarSecondary),
                    textColor = context.getColorFromResources(R.color.white)
                )
            }
            Image(
                modifier = Modifier
                    .size(ICON_SMALL_SIZE).clickable { onClickActionMore() },
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
    CardOverviewTask(50, {}) {}
}