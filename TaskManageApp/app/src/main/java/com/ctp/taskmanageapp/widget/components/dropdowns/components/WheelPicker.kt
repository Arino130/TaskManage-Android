package com.ctp.taskmanageapp.widget.components.dropdowns.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.presentation.common.COLUMN_CONTENT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.common.TEXT_SIZE_H3
import com.ctp.taskmanageapp.presentation.common.TEXT_SIZE_H4
import com.ctp.taskmanageapp.presentation.extensions.getColorFromResources

@Composable
fun WheelPicker(
    context: Context,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val selectedIndex = items.indexOf(selectedItem)
    if (selectedIndex >= 0) {
        LaunchedEffect(selectedIndex) {
            listState.animateScrollToItem(selectedIndex)
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier.height(COLUMN_CONTENT_SIZE),
        contentPadding = PaddingValues(vertical = SPACE_CONTENT_SIZE),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(items) { index, item ->
            val isSelected = index == selectedIndex
            val color = if (isSelected) context.getColorFromResources(R.color.text_blank_color)
            else context.getColorFromResources(R.color.sub_text_dark)
            val fontSize = if (isSelected) TEXT_SIZE_H3 else TEXT_SIZE_H4
            val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal

            Text(
                text = item,
                color = color,
                fontSize = fontSize,
                fontWeight = fontWeight,
                modifier = Modifier
                    .padding(vertical = SPACE_SMALL_8_SIZE)
                    .clickable {
                        onItemSelected(item)
                    }
            )
        }
    }
}
