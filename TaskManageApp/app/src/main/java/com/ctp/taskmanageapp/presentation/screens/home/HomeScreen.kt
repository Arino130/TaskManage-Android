package com.ctp.taskmanageapp.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.ctp.taskmanageapp.data.local.taskgroups.TaskGroup
import com.ctp.taskmanageapp.data.local.taskgroups.TaskGroupType
import com.ctp.taskmanageapp.presentation.common.AVATAR_DEFAULT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_X2_SIZE
import com.ctp.taskmanageapp.presentation.common.h1TitleStyle
import com.ctp.taskmanageapp.presentation.common.h2TextStyle
import com.ctp.taskmanageapp.presentation.common.h4TextStyle
import com.ctp.taskmanageapp.presentation.common.h5TextStyle
import com.ctp.taskmanageapp.presentation.screens.home.component.CardOverviewTask
import com.ctp.taskmanageapp.presentation.screens.home.component.ProgressGroupListView
import com.ctp.taskmanageapp.presentation.screens.home.component.TaskGroupListView
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel
import com.ctp.taskmanageapp.widget.components.labels.LabelCircular

@Composable
fun HomeScreen(mainViewModel: MainViewModel) {
    val context = LocalContext.current
    val inProgressNumber = 10
    val taskGroupNumber = 4
    val taskGroups = listOf(
        TaskGroup(
            titleGroup = "Grocery shopping app design",
            taskGroupType = TaskGroupType.OfficeProject
        ),
        TaskGroup(
            titleGroup = "Grocery shopping app design by Phuong",
            taskGroupType = TaskGroupType.PersonalProject
        ),
        TaskGroup(
            titleGroup = "Grocery shopping app design by Phuong",
            taskGroupType = TaskGroupType.OfficeProject
        ),
        TaskGroup(
            titleGroup = "Grocery shopping app design by Phuong",
            taskGroupType = TaskGroupType.PersonalProject
        ),
        TaskGroup(
            titleGroup = "Grocery shopping app design by Phuong",
            taskGroupType = TaskGroupType.OfficeProject
        )
    )

    LaunchedEffect(Unit) {
        mainViewModel.toggleBottomBar(true)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = PaddingValues(top = SPACE_CONTENT_SIZE))
    ) {
        Row(
            modifier = Modifier.padding(
                paddingValues = PaddingValues(horizontal = SPACE_CONTENT_SIZE)
            )
        ) {
            Image(
                modifier = Modifier
                    .size(AVATAR_DEFAULT_SIZE),
                painter = painterResource(id = R.drawable.ic_generic_avatar),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier.padding(
                    paddingValues = PaddingValues(horizontal = SPACE_SMALL_X2_SIZE)
                ),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = context.getString(R.string.home_content_hello),
                    style = h4TextStyle,
                    color = Color(
                        ContextCompat.getColor(
                            context,
                            R.color.sub_text_dark
                        )
                    )
                )
                Text(
                    text = context.getString(R.string.home_username_mock),
                    style = h2TextStyle,
                    color = Color(
                        ContextCompat.getColor(
                            context,
                            R.color.text_blank_color
                        )
                    )
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier.padding(
                    paddingValues = PaddingValues(
                        horizontal = SPACE_CONTENT_SIZE
                    )
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(paddingValues = PaddingValues(horizontal = 4.dp)),
                ) {
                    Box(modifier = Modifier.padding(top = SPACE_CONTENT_SIZE)) {
                        CardOverviewTask()
                    }
                    Row(
                        modifier = Modifier.padding(
                            paddingValues = PaddingValues(
                                top = SPACE_CONTENT_SIZE
                            )
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = context.getString(R.string.home_in_progress),
                            style = h1TitleStyle,
                            color = Color(
                                ContextCompat.getColor(
                                    context,
                                    R.color.text_blank_color
                                )
                            )
                        )
                        Box(
                            modifier = Modifier.padding(
                                paddingValues = PaddingValues(horizontal = 6.dp)
                            )
                        )
                        {
                            LabelCircular(inProgressNumber.toString(), textStyle = h5TextStyle)
                        }
                    }
                    Box(modifier = Modifier.padding(vertical = 15.dp)) {
                        ProgressGroupListView(taskGroups)
                    }
                    Row(
                        modifier = Modifier.padding(paddingValues = PaddingValues(top = 8.dp)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = context.getString(R.string.home_task_group),
                            style = h1TitleStyle,
                            color = Color(
                                ContextCompat.getColor(
                                    context,
                                    R.color.text_blank_color
                                )
                            )
                        )
                        Box(
                            modifier = Modifier.padding(
                                paddingValues = PaddingValues(horizontal = 6.dp)
                            )
                        )
                        {
                            LabelCircular(taskGroupNumber.toString(), textStyle = h5TextStyle)
                        }
                    }
                    Box(modifier = Modifier.padding(vertical = SPACE_CONTENT_SIZE)) {
                        TaskGroupListView(taskGroups)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenReview() {
    HomeScreen(MainViewModel())
}
