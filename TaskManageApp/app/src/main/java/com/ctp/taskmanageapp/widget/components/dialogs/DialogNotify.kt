package com.ctp.taskmanageapp.widget.components.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.presentation.common.ELEVATION_DEFAULT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_20_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_DEFAULT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.common.h1TitleStyle
import com.ctp.taskmanageapp.presentation.common.h3TextLightStyle
import com.ctp.taskmanageapp.presentation.common.h3TextStyle
import com.ctp.taskmanageapp.presentation.extensions.getColorFromResources
import com.ctp.taskmanageapp.widget.components.dialogs.models.DialogInfo
import com.ctp.taskmanageapp.widget.components.dialogs.models.DialogType

@Composable
fun DialogNotify(info: DialogInfo, onDismissRequest: () -> Unit) {
    val context = LocalContext.current
    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(SPACE_CONTENT_SIZE),
            colors = CardDefaults.cardColors(
                containerColor = context.getColorFromResources(R.color.white)
            ),
            elevation = CardDefaults.cardElevation(ELEVATION_DEFAULT_SIZE)
        ) {
            Column(modifier = Modifier.padding(SPACE_CONTENT_SIZE)) {
                Text(
                    text = context.getString(info.title),
                    color = context.getColorFromResources(R.color.text_blank_color),
                    style = h1TitleStyle
                )
                Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
                Text(
                    text = context.getString(info.body),
                    color = context.getColorFromResources(R.color.text_blank_color),
                    style = h3TextLightStyle
                )
                Spacer(modifier = Modifier.padding(top = SPACE_CONTENT_20_SIZE))
                when (info.type) {
                    DialogType.CONFIRM -> {
                        Box(modifier = Modifier
                            .align(Alignment.End)
                            .clickable {
                                info.onConfirm()
                            }) {
                            Text(
                                text = context.getString(R.string.confirm),
                                color = context.getColorFromResources(R.color.text_primary_color),
                                style = h3TextStyle
                            )
                        }
                    }

                    DialogType.YES_NO -> {
                        Box(
                            modifier = Modifier
                                .align(Alignment.End)
                        ) {
                            Row {
                                Text(
                                    text = context.getString(R.string.yes),
                                    color = context.getColorFromResources(R.color.text_primary_color),
                                    style = h3TextStyle,
                                    modifier = Modifier.clickable { info.onYes() }
                                )
                                Spacer(modifier = Modifier.size(SPACE_DEFAULT_SIZE))
                                Text(
                                    text = context.getString(R.string.no),
                                    color = context.getColorFromResources(R.color.text_primary_color),
                                    style = h3TextStyle,
                                    modifier = Modifier.clickable { info.onNo() }
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun DialogNotifyPreview() {
    DialogNotify(
        DialogInfo(
            R.string.dialog_confirm_done_task_title,
            R.string.dialog_confirm_done_task_body,
            DialogType.CONFIRM
        )
    ) {}
}
