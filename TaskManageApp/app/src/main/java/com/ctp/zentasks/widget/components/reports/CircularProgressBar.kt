package com.ctp.zentasks.widget.components.reports

import android.graphics.Paint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ctp.zentasks.R
import com.ctp.zentasks.presentation.common.COLUMN_CONTENT_SIZE
import com.ctp.zentasks.presentation.extensions.getColorFromResources
import kotlin.math.*

@Composable
fun CircularProgressBar(
    size: Dp = 200.dp,
    initialValue: Int,
    primaryColor: Color,
    secondaryColor: Color,
    textColor: Color,
    minValue: Int = 0,
    maxValue: Int = 100,
    onPositionChange: (Int) -> Unit = {}
) {

    val durationMillis = remember { 500 }
    val circleCenter = remember { mutableStateOf(Offset.Zero) }
    val positionValue = remember { mutableStateOf(initialValue) }
    val oldPositionValue = remember { mutableStateOf(initialValue) }
    val density = LocalDensity.current
    val circleRadius = with(density) { (size.toPx() / 2) - (size.toPx() / 25) }
    val animatedValue = remember { Animatable(0f) }
    Box {
        Canvas(
            modifier = Modifier
                .size(size)
                .align(Alignment.Center)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDrag = { change, _ ->
                            val touchAngle =
                                calculateTouchAngle(change.position, circleCenter.value)
                            val currentAngle = animatedValue.value.toAngle(maxValue, minValue)

                            if (touchAngle.isWithinThreshold(currentAngle)) {
                                positionValue.value = (oldPositionValue.value
                                        + (touchAngle - currentAngle).toPositionValue(
                                    maxValue, minValue)).coerceIn(minValue, maxValue)
                            }
                        },
                        onDragEnd = {
                            oldPositionValue.value = animatedValue.value.toInt()
                            onPositionChange(positionValue.value)
                        }
                    )
                }
        ) {
            val circleThickness = with(density) { size.toPx() / 12f }
            circleCenter.value = Offset(size.toPx() / 2f, size.toPx() / 2f)

            drawCircle(
                color = secondaryColor,
                radius = circleRadius,
                center = circleCenter.value,
                style = Stroke(width = circleThickness)
            )

            drawArc(
                color = primaryColor,
                startAngle = 90f,
                sweepAngle = (360f / maxValue) * animatedValue.value,
                useCenter = false,
                topLeft = Offset((size.toPx()
                        - circleRadius * 2f) / 2f, (size.toPx() - circleRadius * 2f) / 2f),
                size = Size(circleRadius * 2f, circleRadius * 2f),
                style = Stroke(width = circleThickness, cap = StrokeCap.Round)
            )

            drawContext.canvas.nativeCanvas.apply {
                val text = "${animatedValue.value.toInt()} %"
                val textPaint = Paint().apply {
                    textSize = with(density) { size.toPx() / 5 }
                    textAlign = Paint.Align.CENTER
                    color = textColor.toArgb()
                    isFakeBoldText = true
                }
                val x = circleCenter.value.x
                val y = circleCenter.value.y + textPaint.textSize / 2.8f

                drawText(
                    text,
                    x,
                    y,
                    textPaint
                )
            }
        }
    }

    LaunchedEffect(initialValue) {
        animatedValue.animateTo(
            targetValue = initialValue.toFloat(),
            animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)
        )
    }
}


private fun calculateTouchAngle(position: Offset, center: Offset): Float {
    val angle = -atan2(center.y - position.y, center.x - position.x) * (180f / Math.PI).toFloat()
    return (angle + 180f).mod(360f)
}

private fun Float.isWithinThreshold(currentAngle: Float, threshold: Float = 18f) =
    this in (currentAngle - threshold)..(currentAngle + threshold)

private fun Float.toAngle(maxValue: Int, minValue: Int) = this * 360f / (maxValue - minValue)

private fun Float.toPositionValue(maxValue: Int, minValue: Int) =
    (this / (360f / (maxValue - minValue))).roundToInt()

@Preview(showBackground = true)
@Composable
fun Preview() {
    val context = LocalContext.current
    CircularProgressBar(
        initialValue = 50,
        size = COLUMN_CONTENT_SIZE,
        primaryColor = context.getColorFromResources(R.color.CircularProgressBarPrimary),
        secondaryColor = context.getColorFromResources(R.color.CircularProgressBarSecondary),
        textColor = context.getColorFromResources(R.color.white),
        onPositionChange = {

        }
    )
}