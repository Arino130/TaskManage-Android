package com.ctp.taskmanageapp.widget.components.snackbar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.ctp.taskmanageapp.domain.models.SnackBarType
import com.ctp.taskmanageapp.domain.models.Type
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_SIZE
import kotlinx.coroutines.delay
import kotlin.math.abs
import kotlin.math.roundToInt

object SnackBarController {
    val _msg = mutableStateOf<Int?>(null)
    val msg: State<Int?> = _msg

    fun showSnackBar(
        snackBarType: SnackBarType,
        delay: Long = 2000,
        actionText: String? = null,
        onClickAction: () -> Unit = {}
    ) {
        _msg.value = snackBarType.messageId
        SnackBarController.delay = delay
        SnackBarController.actionText = actionText
        type = snackBarType.type
        SnackBarController.onClickAction = onClickAction
    }

    var delay: Long = 2000
    var actionText: String? = null
    var type: Type = Type.ERROR
    var onClickAction: () -> Unit = {}
}

@Composable
fun SnackBarApp() {
    val snackBarMessage = SnackBarController.msg.value
    snackBarMessage?.let {
        SnackBar(
            message = it,
            delay = SnackBarController.delay,
            actionText = SnackBarController.actionText,
            type = SnackBarController.type,
            onClickAction = SnackBarController.onClickAction,
            onDismiss = { SnackBarController._msg.value = null }
        )
    }
}

@Composable
fun SnackBar(
    message: Int,
    delay: Long,
    actionText: String?,
    type: Type,
    onClickAction: () -> Unit,
    onDismiss: () -> Unit
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    val offsetXState by animateFloatAsState(targetValue = offsetX, label = "")
    val translateY = 250f
    val verticalTranslate = remember { Animatable(translateY) }
    val configuration = LocalConfiguration.current
    val deviceWidthPixels = configuration.screenWidthDp * LocalDensity.current.density

    LaunchedEffect(message) {
        verticalTranslate.animateTo(0f, tween(500))
        delay(delay)
        verticalTranslate.animateTo(translateY, tween(500))
        onDismiss()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(SPACE_CONTENT_SIZE),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(offsetXState.roundToInt(), 0) }
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta -> offsetX += delta },
                    onDragStopped = {
                        val threshold = deviceWidthPixels * 0.3f
                        if (abs(offsetX) > threshold) onDismiss() else offsetX = 0f
                    }
                )
                .background(type.actionColor, RoundedCornerShape(8.dp))
                .graphicsLayer { translationY = verticalTranslate.value },
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = LocalContext.current.getString(message),
                    color = type.onSecondary,
                    modifier = Modifier.weight(1f)
                )
                actionText?.let {
                    Text(
                        text = it,
                        color = type.actionColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { onClickAction() }
                    )
                }
            }
        }
    }
}
