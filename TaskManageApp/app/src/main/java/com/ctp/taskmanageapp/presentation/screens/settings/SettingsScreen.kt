package com.ctp.taskmanageapp.presentation.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ctp.taskmanageapp.R
import com.ctp.taskmanageapp.presentation.common.SPACE_CONTENT_SIZE
import com.ctp.taskmanageapp.presentation.common.SPACE_SMALL_8_SIZE
import com.ctp.taskmanageapp.presentation.common.h4TextStyle
import com.ctp.taskmanageapp.presentation.extensions.getColorFromResources
import com.ctp.taskmanageapp.presentation.viewmodels.MainViewModel
import com.ctp.taskmanageapp.widget.components.buttons.ButtonTMComponent
import com.ctp.taskmanageapp.widget.components.buttons.ButtonType
import com.ctp.taskmanageapp.widget.components.dialogs.DialogNotify
import com.ctp.taskmanageapp.widget.components.dialogs.models.DialogInfo
import com.ctp.taskmanageapp.widget.components.dialogs.models.DialogType
import com.ctp.taskmanageapp.widget.components.headers.HeaderScreen

@Composable
fun SettingsScreen(mainViewModel: MainViewModel, onClickAboutUs: () -> Unit) {
    val context = LocalContext.current
    val showConfirmResetData = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        mainViewModel.toggleBottomBar(true)
    }
    Column {
        HeaderScreen(titleId = R.string.settings_screen_title)
        Spacer(modifier = Modifier.padding(top = SPACE_CONTENT_SIZE))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = SPACE_CONTENT_SIZE)
        ) {
            ButtonTMComponent(
                titleButton = context.getString(R.string.settings_screen_button_rating_title),
                iconButtonR = R.drawable.ic_rate_us,
                buttonType = ButtonType.Primary,
            ) {
                mainViewModel.rateUs(context)
            }
            Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
            ButtonTMComponent(
                titleButton = context.getString(R.string.settings_screen_button_store_more_title),
                iconButtonR = R.drawable.ic_discover,
                buttonType = ButtonType.Primary,
            ) { 
                mainViewModel.discoverMore(context)
            }
            Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
            ButtonTMComponent(
                titleButton = context.getString(R.string.settings_screen_button_about_title),
                iconButtonR = R.drawable.ic_about_app,
                buttonType = ButtonType.Primary,
            ) {
                onClickAboutUs()
            }
            Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
            ButtonTMComponent(
                titleButton = context.getString(R.string.settings_screen_button_share_title),
                iconButtonR = R.drawable.ic_share_app,
                buttonType = ButtonType.Primary,
            ) {
                mainViewModel.shareMyApp(context)
            }
            Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
            ButtonTMComponent(
                titleButton = context.getString(R.string.settings_screen_button_reset_data_title),
                iconButtonR = R.drawable.ic_restart_data,
                buttonType = ButtonType.Primary,
            ) {
                showConfirmResetData.value = true
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = context.getString(
                    R.string.settings_screen_version_name,
                    mainViewModel.buildVersionName(context)
                ),
                style = h4TextStyle,
                color = context.getColorFromResources(R.color.sub_text_dark),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(top = SPACE_SMALL_8_SIZE))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = context.getString(
                    R.string.settings_screen_code_name,
                    mainViewModel.buildVersionCode(context)
                ),
                style = h4TextStyle,
                color = context.getColorFromResources(R.color.sub_text_dark),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(bottom = SPACE_SMALL_8_SIZE))
        }
    }
    if (showConfirmResetData.value) {
        DialogNotify(
            DialogInfo(
                R.string.dialog_confirm_reset_data_title,
                R.string.dialog_confirm_reset_data_body,
                DialogType.CONFIRM,
                onConfirm = {
                    showConfirmResetData.value = false
                    mainViewModel.resetAllData()
                }
            )
        ) { showConfirmResetData.value = false }
    }
}

@Preview
@Composable
fun SettingsScreenReview() {
    SettingsScreen(MainViewModel(null, null)) {}
}