package com.ctp.zentasks.widget.components.dropdowns.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.ctp.zentasks.R
import com.ctp.zentasks.domain.models.taskgroups.TaskGroupType
import com.ctp.zentasks.presentation.common.DEFAULT_HEIGHT_SIZE
import com.ctp.zentasks.presentation.common.ICON_SMALL_SIZE
import com.ctp.zentasks.presentation.common.SPACE_CONTENT_SIZE
import com.ctp.zentasks.presentation.common.SPACE_DEFAULT_SIZE
import com.ctp.zentasks.presentation.common.SPACE_SMALL_12_SIZE
import com.ctp.zentasks.presentation.common.h3TextStyle
import com.ctp.zentasks.presentation.extensions.getColorFromResources
import com.ctp.zentasks.widget.components.dropdowns.models.DropDownTMModel

@Composable
fun <T> DropDownTMItem(
    item: DropDownTMModel<T>,
    onSelected: (DropDownTMModel<T>) -> Unit
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = DEFAULT_HEIGHT_SIZE)
            .clickable { onSelected(item) },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = context.getColorFromResources(
                R.color.white
            )
        )
    ) {
        Box(
            modifier = Modifier
                .padding(
                    paddingValues =
                    PaddingValues(start = SPACE_CONTENT_SIZE, end = SPACE_CONTENT_SIZE)
                )
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = SPACE_DEFAULT_SIZE)
            ) {
                Image(
                    modifier = Modifier
                        .size(ICON_SMALL_SIZE),
                    painter = painterResource(id = item.iconId),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    modifier = Modifier
                        .padding(
                            paddingValues =
                            PaddingValues(
                                horizontal = SPACE_SMALL_12_SIZE
                            )
                        ),
                    text = context.getString(item.titleId),
                    style = h3TextStyle,
                    color = Color(
                        ContextCompat.getColor(
                            context,
                            R.color.text_blank_color
                        )
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun DropDownTMItemPreview() {
    DropDownTMItem(
        DropDownTMModel(
            titleId = TaskGroupType.OfficeProject.typeTitleId,
            iconId = TaskGroupType.OfficeProject.typeIcon,
            rootData = TaskGroupType.OfficeProject
        )
    ){}
}
