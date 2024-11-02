package com.ctp.taskmanageapp.widget.components.buttons

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.presentation.common.ELEVATION_DEFAULT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_SEGMENT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_6_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.common.h4TextStyle
import com.ctp.taskmanageapp.presentation.extensions.getColorFromResources
import com.ctp.taskmanageapp.widget.components.buttons.model.SegmentModel

@Composable
fun SegmentedControl(
    segmentData: List<SegmentModel>,
    onSegmentSelected: (SegmentModel) -> Unit
) {
    val selectedSegment = remember {
        mutableStateOf(segmentData.firstOrNull { it.isSelected }) }
    val context = LocalContext.current
    val listState = rememberLazyListState()
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(SPACE_SMALL_8_SIZE),
    ) {
        items(segmentData) { item ->
            SegmentedItem(
                context, item,
                isSelected = (item == selectedSegment.value)
            ) {
                selectedSegment.value = item
                onSegmentSelected(item)
            }
        }
    }
}

@Composable
fun SegmentedItem(
    context: Context,
    segmentItem: SegmentModel,
    isSelected: Boolean,
    onSegmentSelected: (SegmentModel) -> Unit
) {
    val backgroundColor = if (isSelected)
        context.getColorFromResources(R.color.button_background_primary)
    else context.getColorFromResources(R.color.button_background_light)

    val textColor = if (isSelected) Color.White
    else context.getColorFromResources(R.color.button_background_primary)
    Card(
        modifier = Modifier
            .clickable { onSegmentSelected(segmentItem) }
            .wrapContentWidth()
            .height(42.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(ELEVATION_DEFAULT_SIZE),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    paddingValues = PaddingValues(
                        vertical = SPACE_SMALL_6_SIZE,
                        horizontal = SPACE_CONTENT_SEGMENT_SIZE
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = segmentItem.textId?.let {
                    context.getString(it)
                } ?: segmentItem.textStr,
                color = textColor,
                style = h4TextStyle
            )
        }
    }
}

@Preview
@Composable
fun SegmentedControlPreview() {
    SegmentedControl(
        listOf(
            SegmentModel(textStr = "All"),
            SegmentModel(textStr = "Todo"),
            SegmentModel(textStr = "In Progress"),
            SegmentModel(textStr = "Done")
        )
    ) {

    }
}