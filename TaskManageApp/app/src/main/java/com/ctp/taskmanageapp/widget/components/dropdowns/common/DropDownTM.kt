package com.ctp.taskmanageapp.widget.components.dropdowns.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.domain.models.TaskGroupType
import com.ctp.taskmanageapp.presentation.common.DEFAULT_HEIGHT_SIZE
import com.ctp.taskmanageapp.presentation.common.ICON_DROPDOWN_SIZE
import com.ctp.taskmanageapp.presentation.common.ICON_SMALL_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_DEFAULT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_12_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.common.h3TextStyle
import com.ctp.taskmanageapp.presentation.extensions.getColorFromResources
import com.ctp.taskmanageapp.widget.components.dropdowns.models.DropDownTMModel

@Composable
fun <T> DropDownTM(
    items: List<DropDownTMModel<T>>,
    selectDefault: DropDownTMModel<T>? = null,
    onSelected: (DropDownTMModel<T>) -> Unit
) {
    val context = LocalContext.current
    val selected = remember {
        mutableStateOf(
            selectDefault ?: items.first().also { onSelected(it) }
        )
    }
    val isDropdown = remember { mutableStateOf(false) }
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = DEFAULT_HEIGHT_SIZE)
                .clickable { isDropdown.value = !isDropdown.value },
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
                        painter = painterResource(id = selected.value.iconId),
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
                        text = context.getString(selected.value.titleId),
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
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        modifier = Modifier
                            .size(ICON_DROPDOWN_SIZE),
                        painter = painterResource(
                            id = if (isDropdown.value) R.drawable.ic_collapse
                            else R.drawable.ic_dropdown
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(bottom = SPACE_SMALL_8_SIZE))
        if (isDropdown.value) {
            Column {
                items.forEach { item ->
                    DropDownTMItem(item) {
                        isDropdown.value = !isDropdown.value
                        selected.value = it
                        onSelected(it)
                    }
                    Spacer(modifier = Modifier.padding(bottom = SPACE_SMALL_8_SIZE))
                }
            }
        }
    }
}

@Preview
@Composable
fun DropDownTMPreview() {
    DropDownTM(
        listOf(
            DropDownTMModel(
                titleId = TaskGroupType.OfficeProject.typeTitleId,
                iconId = TaskGroupType.OfficeProject.typeIcon,
                rootData = TaskGroupType.OfficeProject
            ),
            DropDownTMModel(
                titleId = TaskGroupType.PersonalProject.typeTitleId,
                iconId = TaskGroupType.PersonalProject.typeIcon,
                rootData = TaskGroupType.PersonalProject
            )
        )
    ){}
}