package com.ctp.zentasks.presentation.screens.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ctp.zentasks.R
import com.ctp.zentasks.presentation.common.h3TextStyle
import com.ctp.zentasks.presentation.extensions.getColorFromResources
import com.ctp.zentasks.domain.models.taskgroups.TaskGroupType
import com.ctp.zentasks.presentation.common.COLUMN_CONTENT_SIZE
import com.ctp.zentasks.presentation.common.ELEVATION_DEFAULT_SIZE
import com.ctp.zentasks.presentation.common.ICON_SMALL_SIZE
import com.ctp.zentasks.presentation.common.SPACE_DEFAULT_SIZE
import com.ctp.zentasks.presentation.common.SPACE_SMALL_10_SIZE
import com.ctp.zentasks.presentation.common.SPACE_SMALL_6_SIZE
import com.ctp.zentasks.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.zentasks.presentation.common.h4TextStyle
import com.ctp.zentasks.widget.components.reports.LineProgressBar

@Composable
fun CardTaskGroup(
    groupType: TaskGroupType,
    groupTitle: String,
    progress: Int,
    onClick: (TaskGroupType) -> Unit
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .height(COLUMN_CONTENT_SIZE)
            .width(260.dp).clickable { onClick(groupType) },
        elevation = CardDefaults.cardElevation(defaultElevation = ELEVATION_DEFAULT_SIZE),
        colors = CardDefaults.cardColors(
            containerColor = context.getColorFromResources(
                groupType.background
            )
        )
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(
                    horizontal = SPACE_DEFAULT_SIZE,
                    vertical = SPACE_SMALL_10_SIZE
                )
                .fillMaxSize(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = context.getString(groupType.typeTitleId), style = h4TextStyle,
                    color = context.getColorFromResources(
                        R.color.sub_text_dark
                    )
                )
                Image(
                    modifier = Modifier
                        .size(ICON_SMALL_SIZE),
                    painter = painterResource(id = groupType.typeIcon),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            Text(
                modifier = Modifier.padding(
                    paddingValues = PaddingValues(vertical = SPACE_SMALL_8_SIZE)
                ),
                text = groupTitle,
                style = h3TextStyle,
                color = context.getColorFromResources(
                    R.color.text_blank_color
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            LineProgressBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        paddingValues = PaddingValues(
                            vertical = SPACE_DEFAULT_SIZE,
                            horizontal = SPACE_SMALL_6_SIZE
                        )
                    ),
                progress = progress,
                backgroundColor = Color.White,
                progressColor = context.getColorFromResources(groupType.progressLineColor),
                barHeight = SPACE_SMALL_10_SIZE
            )
        }
    }
}

@Preview
@Composable
fun CardTaskGroupPreview() {
    CardTaskGroup(
        groupType = TaskGroupType.OfficeProject,
        "Grocery shopping app design",
        progress = 80
    ) {}
}