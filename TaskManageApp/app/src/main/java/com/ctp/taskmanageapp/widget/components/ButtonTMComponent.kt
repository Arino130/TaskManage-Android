package com.ctp.taskmanageapp.widget.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.presentation.common.buttonTextPrimaryStyle

@Composable
fun ButtonTMComponent(titleButton: String, iconButtonL: Int? = null, iconButtonR: Int? = null, buttonType: ButtonType, onClickEvent: () -> Unit) {
    val context = LocalContext.current
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClickEvent,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(ContextCompat.getColor(context, buttonType.containerColor)),
            contentColor = Color(
                ContextCompat.getColor(
                    context,
                    buttonType.contentColor
                )
            )
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            iconButtonL?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    tint = Color(
                        ContextCompat.getColor(
                            context,
                            buttonType.contentColor
                        )
                    ),
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                text = titleButton,
                style = buttonTextPrimaryStyle,
                modifier = Modifier.weight(1f)
            )
            iconButtonR?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    tint = Color(
                        ContextCompat.getColor(
                            context,
                            buttonType.contentColor
                        )
                    ),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

enum class ButtonType(
    var containerColor: Int,
    var contentColor: Int
) {
    Primary(
        containerColor = R.color.button_background_primary,
        contentColor = R.color.button_content_primary
    ),
    Default(
        containerColor = R.color.button_background_default,
        contentColor = R.color.button_content_default
    )
}

@Preview
@Composable
fun ButtonTMComponentReview() {
    ButtonTMComponent("Click Test",
        iconButtonL = R.drawable.ic_arrow_right,
        iconButtonR = R.drawable.ic_arrow_right, ButtonType.Primary) {}
}