package com.ctp.taskmanageapp.widget.components.swipe

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.presentation.common.h3TextStyle
import com.ctp.taskmanageapp.presentation.extensions.getColorFromResources

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SwipeActionBox(
    item: T,
    onAction: (T) -> Unit,
    bgColorId: Int = R.color.swipe_background_color,
    titleUnderId: Int = R.string.see_details,
    animationDuration: Int = 300,
    content: @Composable (T) -> Unit
) {
    val context = LocalContext.current
    val bgColor = context.getColorFromResources(bgColorId)
    val state = rememberDismissState(
        initialValue = DismissValue.Default,
        confirmValueChange = { dismissValue ->
            if (dismissValue == DismissValue.DismissedToStart) {
                onAction(item)
                false
            } else {
                false
            }
        }
    )

    AnimatedVisibility(
        visible = true,
        exit = fadeOut(tween(animationDuration))
    ) {
        SwipeToDismiss(
            state = state,
            background = { ActionBackground(dismissState = state, bgColor, titleUnderId, context) },
            dismissContent = { content(item) },
            directions = setOf(DismissDirection.EndToStart)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun ActionBackground(
    dismissState: DismissState,
    bgColor: Color,
    titleUnderId: Int,
    context: Context
) {
    val color = if (dismissState.dismissDirection == DismissDirection.EndToStart) bgColor
        else Color.Transparent
    val alphaValue = if (dismissState.dismissDirection == DismissDirection.EndToStart) 1f else 0f
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color, RoundedCornerShape(8.dp))
            .graphicsLayer { alpha = alphaValue }
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text = context.getString(titleUnderId),
            style = h3TextStyle,
            color = Color(
                ContextCompat.getColor(
                    context,
                    R.color.text_blank_color
                )
            )
        )
    }
}
