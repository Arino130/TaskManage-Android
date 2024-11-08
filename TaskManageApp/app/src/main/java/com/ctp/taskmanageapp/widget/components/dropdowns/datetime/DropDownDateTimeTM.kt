package com.ctp.taskmanageapp.widget.components.dropdowns.datetime

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import com.ctp.taskmanageapp.domain.extensions.formatDatetimeTask
import com.ctp.taskmanageapp.presentation.common.DEFAULT_HEIGHT_SIZE
import com.ctp.taskmanageapp.presentation.common.ICON_DROPDOWN_SIZE
import com.ctp.taskmanageapp.presentation.common.ICON_MINI_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_12_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.common.h3TextStyle
import com.ctp.taskmanageapp.presentation.common.h4TextStyle
import com.ctp.taskmanageapp.presentation.extensions.getColorFromResources
import com.ctp.taskmanageapp.widget.components.dropdowns.components.DateTimePicker
import java.time.LocalDateTime

@Composable
fun DropDownDateTimeTM(
    titleId: Int,
    readOnly: Boolean = false,
    value: LocalDateTime? = null,
    plusHours: Long? = null,
    onChanged: (LocalDateTime) -> Unit
) {
    val context = LocalContext.current
    val onChangeLocalDateTime = remember {
        val adjustedValue = value?.withSecond(0)?.withNano(0)
        mutableStateOf(adjustedValue?.takeIf { it.isAfter(LocalDateTime.now()) }
            ?: LocalDateTime.now()
                .withSecond(0)
                .withNano(0).apply {
                    plusHours?.let { plusHours(plusHours) }
                }
        )
    }
    val isDropdown = remember { mutableStateOf(false) }
    LaunchedEffect(plusHours) {
        plusHours?.let {
            onChangeLocalDateTime.value = onChangeLocalDateTime.value.plusHours(it)
            onChanged(onChangeLocalDateTime.value)
        }
    }
    LaunchedEffect(readOnly, onChangeLocalDateTime.value, value) {
        if (readOnly) {
            isDropdown.value = false
        }
        value?.let {
            onChangeLocalDateTime.value = it
        }
        onChanged(onChangeLocalDateTime.value)
    }
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = DEFAULT_HEIGHT_SIZE)
                .clickable {
                    if (!readOnly) {
                        isDropdown.value = !isDropdown.value
                    }
                },
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = context.getColorFromResources(
                    R.color.white
                )
            )
        ) {
            Box(
                modifier = Modifier
                    .padding(start = SPACE_CONTENT_SIZE, end = SPACE_CONTENT_SIZE)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = SPACE_SMALL_12_SIZE)
                ) {
                    Image(
                        modifier = Modifier
                            .size(ICON_MINI_SIZE),
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                    Column {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = SPACE_SMALL_12_SIZE),
                            text = context.getString(titleId),
                            style = h4TextStyle,
                            color = Color(
                                ContextCompat.getColor(
                                    context,
                                    R.color.sub_text_dark
                                )
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            modifier = Modifier
                                .padding(horizontal = SPACE_SMALL_12_SIZE),
                            text = onChangeLocalDateTime.value.formatDatetimeTask(),
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
                    Spacer(modifier = Modifier.weight(1f))
                    if (!readOnly) {
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
            if (isDropdown.value) {
                Box(
                    modifier = Modifier
                        .padding(vertical = SPACE_SMALL_8_SIZE)
                        .fillMaxWidth()
                ) {
                    DateTimePicker(onChangeLocalDateTime.value) {
                        onChangeLocalDateTime.value = it
                        onChanged(it)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DropDownTMPreview() {
    DropDownDateTimeTM(R.string.add_task_screen_start_datetime_title) {}
}
