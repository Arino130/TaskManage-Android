package com.ctp.taskmanageapp.presentation.screens.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.presentation.common.h4TextStyle
import com.ctp.taskmanageapp.presentation.extensions.getColorFromResources
import com.ctp.taskmanageapp.data.local.taskgroups.TaskGroupType
import com.ctp.taskmanageapp.presentation.common.CIRCULAR_SMALL_SIZE
import com.ctp.taskmanageapp.presentation.common.ICON_LARGE_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_12_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_4_SIZE
import com.ctp.taskmanageapp.presentation.common.h3TextStyle
import com.ctp.taskmanageapp.widget.components.reports.CircularProgressBar

@Composable
fun CardGroupTaskItem(groupType: TaskGroupType, taskCount: Int = 0, progress: Int) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = context.getColorFromResources(
                R.color.white
            )
        )
    ) {
        Box(
            modifier = Modifier.padding(
                paddingValues =
                PaddingValues(start = SPACE_CONTENT_SIZE, end = SPACE_CONTENT_SIZE)
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues = PaddingValues(vertical = SPACE_CONTENT_SIZE))
            ) {
                Image(
                    modifier = Modifier
                        .size(ICON_LARGE_SIZE),
                    painter = painterResource(id = groupType.typeIcon),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(
                            paddingValues =
                            PaddingValues(
                                vertical = SPACE_SMALL_4_SIZE, horizontal = SPACE_SMALL_12_SIZE
                            )
                        ),
                ) {
                    Text(
                        text = context.getString(groupType.typeTitleId),
                        style = h3TextStyle,
                        color = Color(
                            ContextCompat.getColor(
                                context,
                                R.color.text_blank_color
                            )
                        )
                    )
                    Text(
                        text = context.getString(R.string.task_count, taskCount),
                        style = h4TextStyle,
                        color = Color(
                            ContextCompat.getColor(
                                context,
                                R.color.sub_text_dark
                            )
                        )
                    )
                }
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    CircularProgressBar(
                        initialValue = progress,
                        size = CIRCULAR_SMALL_SIZE,
                        primaryColor =
                        context.getColorFromResources(groupType.progressCircularColor),
                        secondaryColor =
                        context.getColorFromResources(groupType.progressCircularSecondaryColor),
                        textColor = context.getColorFromResources(R.color.text_blank_color)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CardGroupTaskItemPreview() {
    CardGroupTaskItem(TaskGroupType.OfficeProject, 5, 80)
}