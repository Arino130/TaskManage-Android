package com.ctp.taskmanageapp.presentation.screens.calendar.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.data.local.filters.StatusTask
import com.ctp.taskmanageapp.data.local.taskgroups.TaskGroupType
import com.ctp.taskmanageapp.data.local.tasks.TaskInfo
import com.ctp.taskmanageapp.presentation.common.ELEVATION_DEFAULT_SIZE
import com.ctp.taskmanageapp.presentation.common.ICON_MINI_SIZE
import com.ctp.taskmanageapp.presentation.common.ICON_SMALL_SIZE
import com.ctp.taskmanageapp.presentation.common.MAX_HEIGHT_CARD_TASK_INFO
import com.ctp.taskmanageapp.presentation.common.SPACE_DEFAULT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_12_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_4_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.common.h3TextStyle
import com.ctp.taskmanageapp.presentation.common.h4TextStyle
import com.ctp.taskmanageapp.presentation.common.h5TextStyle
import com.ctp.taskmanageapp.presentation.extensions.getColorFromResources
import com.ctp.taskmanageapp.widget.components.labels.LabelCircular
import java.time.LocalDateTime

@Composable
fun CalendarTaskItem(task: TaskInfo, onClick: (TaskInfo) -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(MAX_HEIGHT_CARD_TASK_INFO)
            .clickable { onClick(task) },
        colors = CardDefaults.cardColors(
            containerColor = context.getColorFromResources(R.color.white)
        ),
        elevation = CardDefaults.cardElevation(ELEVATION_DEFAULT_SIZE),
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        paddingValues = PaddingValues(
                            horizontal = SPACE_SMALL_12_SIZE
                        )
                    )
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = context.getString(task.taskGroupType.typeTitleId),
                    style = h4TextStyle,
                    color = Color(
                        ContextCompat.getColor(
                            context, R.color.sub_text_dark
                        )
                    )
                )
                Text(
                    modifier = Modifier
                        .padding(
                            paddingValues = PaddingValues(
                                top = SPACE_SMALL_8_SIZE, bottom = SPACE_SMALL_8_SIZE
                            )
                        )
                        .fillMaxWidth(),
                    text = task.titleTask,
                    style = h3TextStyle,
                    color = Color(
                        ContextCompat.getColor(
                            context, R.color.text_blank_color
                        )
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row {
                    Image(
                        modifier = Modifier.size(ICON_MINI_SIZE),
                        painter = painterResource(id = R.drawable.ic_clock),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(modifier = Modifier.size(SPACE_SMALL_4_SIZE))
                    Text(
                        text = task.startTimeFormat,
                        style = h4TextStyle,
                        color = Color(
                            ContextCompat.getColor(
                                context, R.color.sub_text_dark
                            )
                        )
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        paddingValues = PaddingValues(SPACE_SMALL_12_SIZE)
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ) {
                Image(
                    modifier = Modifier
                        .size(ICON_SMALL_SIZE),
                    painter = painterResource(id = task.taskGroupType.typeIcon),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.weight(1f))
                LabelCircular(
                    textId = task.statusTask.fullNameId,
                    colorBackground = task.statusTask.backgroundColor,
                    colorText = task.statusTask.labelColor,
                    textStyle = h5TextStyle
                )
            }
        }
    }
    Spacer(modifier = Modifier.size(SPACE_DEFAULT_SIZE))
}

@Preview
@Composable
fun CalendarTaskItemPreview() {
    CalendarTaskItem(
        TaskInfo(
            taskGroupType = TaskGroupType.OfficeProject,
            titleTask = "Lorem Ipsum is simply dummy text",
            content = "Lorem Ipsum is simply dummy text of the printing",
            startTime = LocalDateTime.now(),
            endTime = LocalDateTime.now().plusDays(2),
            statusTask = StatusTask.IN_PROGRESS
        )
    ) {}
}