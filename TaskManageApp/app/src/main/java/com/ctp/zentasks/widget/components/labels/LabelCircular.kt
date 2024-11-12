package com.ctp.zentasks.widget.components.labels

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.zentasks.R
import com.ctp.zentasks.presentation.common.SPACE_SMALL_4_SIZE
import com.ctp.zentasks.presentation.common.SPACE_SMALL_6_SIZE
import com.ctp.zentasks.presentation.common.h1TitleStyle
import com.ctp.zentasks.presentation.extensions.getColorFromResources

@Composable
fun LabelCircular(
    textStr: String = "",
    textId: Int? = null,
    textStyle: TextStyle,
    colorText: Int = R.color.button_background_primary,
    colorBackground: Int = R.color.label_background
) {
    val context = LocalContext.current
    val colors: CardColors = CardDefaults.cardColors(
        containerColor = context.getColorFromResources(colorBackground)
    )
    Card(
        shape = CircleShape,
        colors = colors
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = SPACE_SMALL_4_SIZE, horizontal = SPACE_SMALL_6_SIZE),
            text = (textId?.let { context.getString(textId) } ?: textStr),
            style = textStyle,
            color = context.getColorFromResources(colorText)
        )
    }
}

@Preview
@Composable
fun Preview() {
    LabelCircular("999", textStyle = h1TitleStyle)
}