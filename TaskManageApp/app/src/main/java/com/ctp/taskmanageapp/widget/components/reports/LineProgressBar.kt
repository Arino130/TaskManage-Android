package com.ctp.taskmanageapp.widget.components.reports

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LineProgressBar(
    modifier: Modifier = Modifier,
    progress: Int,
    backgroundColor: Color = Color.Gray,
    progressColor: Color = Color.Blue,
    barHeight: Dp = 8.dp
) {
    val animatedValue = remember { Animatable(0f) }
    val durationMillis = remember { 500 }
    val progressColorLine = if (progress > 0) progressColor else backgroundColor
    Canvas(modifier = modifier.height(barHeight)) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawLine(
            color = backgroundColor,
            start = Offset(x = 0f, y = canvasHeight / 2),
            end = Offset(x = canvasWidth, y = canvasHeight / 2),
            strokeWidth = barHeight.toPx(),
            cap = StrokeCap.Round
        )

        drawLine(
            color = progressColorLine,
            start = Offset(x = 0f, y = canvasHeight / 2),
            end = Offset(x = canvasWidth * (animatedValue.value * 0.01).toFloat(), y = canvasHeight / 2),
            strokeWidth = barHeight.toPx(),
            cap = StrokeCap.Round
        )
    }
    LaunchedEffect(progress) {
        animatedValue.animateTo(
            targetValue = progress.toFloat(),
            animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)
        )
    }
}

@Preview
@Composable
fun LineProgressBarPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LineProgressBar(
            modifier = Modifier.fillMaxWidth(),
            progress = 50,
            backgroundColor = Color.LightGray,
            progressColor = Color.Blue,
            barHeight = 10.dp
        )
    }
}