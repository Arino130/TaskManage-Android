package com.ctp.taskmanageapp.widget.components.inputs

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.presentation.common.DEFAULT_HEIGHT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_12_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_4_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.common.h3TextLightStyle
import com.ctp.taskmanageapp.presentation.common.h3TextStyle
import com.ctp.taskmanageapp.presentation.extensions.getColorFromResources

@Composable
fun InputTM(
    maxLength: Int = 100,
    inputType: InputType,
    labelId: Int,
    hintId: Int,
    value: String? = null,
    readOnly: Boolean = false,
    onTextChanges: (String) -> Unit
) {
    val context = LocalContext.current
    val textInput = remember { mutableStateOf(value ?: "") }
    val isFocusInput = remember { mutableStateOf(!value.isNullOrBlank()) }

    val animatedTextSize by animateDpAsState(
        targetValue = if (isFocusInput.value) SPACE_SMALL_12_SIZE else SPACE_CONTENT_SIZE,
        label = ""
    )
    val animatedTextPadding by animateDpAsState(
        targetValue = if (isFocusInput.value) SPACE_SMALL_8_SIZE else SPACE_CONTENT_SIZE, label = ""
    )
    var singleLine = remember { false }
    val defaultMinHeight = DEFAULT_HEIGHT_SIZE
    val defaultHeight = when (inputType) {
        InputType.SINGLE_INPUT -> {
            singleLine = true
            defaultMinHeight
        }

        InputType.AREA_INPUT -> {
            singleLine = false
            140.dp
        }
    }
    val cardMinHeight = if (isFocusInput.value) defaultHeight else defaultMinHeight
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = cardMinHeight)
            .clickable {
                if (textInput.value.isEmpty()) {
                    isFocusInput.value = !isFocusInput.value
                }
            },
        elevation = CardDefaults.cardElevation(defaultElevation = SPACE_SMALL_8_SIZE),
        colors = CardDefaults.cardColors(
            containerColor = context.getColorFromResources(R.color.white)
        )
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = SPACE_CONTENT_SIZE, vertical = animatedTextPadding
            ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (isFocusInput.value) context.getString(labelId) else context.getString(
                    hintId
                ),
                fontSize = TextUnit(animatedTextSize.value, TextUnitType.Sp),
                modifier = if (!isFocusInput.value) {
                    Modifier
                        .defaultMinSize(minHeight = defaultMinHeight / 2)
                        .wrapContentHeight(Alignment.CenterVertically)
                } else Modifier,
                textAlign = TextAlign.Start,
                color = context.getColorFromResources(R.color.sub_text_dark),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            if (isFocusInput.value) {
                val focusRequester = remember { FocusRequester() }
                LaunchedEffect(isFocusInput.value) {
                    if (isFocusInput.value) focusRequester.requestFocus()
                }

                BasicTextField(
                    value = textInput.value,
                    textStyle = h3TextStyle,
                    onValueChange = {
                        if (it.length <= maxLength) {
                            textInput.value = it
                            onTextChanges(it)
                        }
                    },
                    readOnly = readOnly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(defaultHeight)
                        .padding(top = SPACE_SMALL_4_SIZE)
                        .background(Color.Transparent)
                        .heightIn(max = (h3TextLightStyle.fontSize.value * 4).dp)
                        .focusRequester(focusRequester),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (textInput.value.isEmpty()) {
                                Text(
                                    text = context.getString(hintId),
                                    style = h3TextLightStyle,
                                    color = context.getColorFromResources(R.color.sub_text_dark),
                                    modifier = Modifier.align(Alignment.TopStart),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            innerTextField()
                        }
                    },
                    singleLine = singleLine
                )
            }
        }
    }
    LaunchedEffect(value) {
        value?.let {
            textInput.value = it
            isFocusInput.value = it.isNotBlank()
        }
    }
}

@Preview
@Composable
fun InputTMPreview() {
    InputTM(
        inputType = InputType.AREA_INPUT,
        labelId = R.string.add_task_screen_task_name_field,
        hintId = R.string.add_task_screen_task_name_field_hint
    ) {}
}
