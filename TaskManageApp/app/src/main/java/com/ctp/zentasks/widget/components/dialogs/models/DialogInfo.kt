package com.ctp.zentasks.widget.components.dialogs.models

data class DialogInfo(
    val title: Int = -1,
    val body: Int = -1,
    val type: DialogType = DialogType.CONFIRM,
    val onConfirm: () -> Unit = {},
    val onYes: () -> Unit = {},
    val onNo: () -> Unit = {}
)

enum class DialogType {
    CONFIRM, YES_NO
}
