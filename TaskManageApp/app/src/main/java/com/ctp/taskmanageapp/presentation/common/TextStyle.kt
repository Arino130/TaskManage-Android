package com.ctp.taskmanageapp.presentation.common

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ctp.taskmanageapp.R

val fontBold = FontFamily(Font(R.font.sf_bold))
val fontHeavy = FontFamily(Font(R.font.sf_heavy))
val fontLight = FontFamily(Font(R.font.sf_light))
val fontMedium = FontFamily(Font(R.font.sf_medium))
val fontRegular = FontFamily(Font(R.font.sf_regular))
val fontSemiBold = FontFamily(Font(R.font.sf_semibold))

val h1TitleStyle = androidx.compose.ui.text.TextStyle(
    fontSize = 24.sp,
    fontFamily = fontBold,
    fontWeight = FontWeight.Bold
)

var h2TextStyle = androidx.compose.ui.text.TextStyle(
    fontSize = 20.sp,
    fontFamily = fontMedium,
    fontWeight = FontWeight.Normal
)

var h3TextStyle = androidx.compose.ui.text.TextStyle(
    fontSize = 16.sp,
    fontFamily = fontMedium,
    fontWeight = FontWeight.Normal
)

var h4TextStyle = androidx.compose.ui.text.TextStyle(
    fontSize = 14.sp,
    fontFamily = fontMedium,
    fontWeight = FontWeight.Normal
)

var h5TextStyle = androidx.compose.ui.text.TextStyle(
    fontSize = 12.sp,
    fontFamily = fontMedium,
    fontWeight = FontWeight.Normal
)

var h6TextStyle = androidx.compose.ui.text.TextStyle(
    fontSize = 10.sp,
    fontFamily = fontMedium,
    fontWeight = FontWeight.Normal
)

var h7TextStyle = androidx.compose.ui.text.TextStyle(
    fontSize = 6.sp,
    fontFamily = fontMedium,
    fontWeight = FontWeight.Normal
)

var h8TextStyle = androidx.compose.ui.text.TextStyle(
    fontSize = 4.sp,
    fontFamily = fontMedium,
    fontWeight = FontWeight.Normal
)

var h9TextStyle = androidx.compose.ui.text.TextStyle(
    fontSize = 2.sp,
    fontFamily = fontMedium,
    fontWeight = FontWeight.Normal
)


var buttonTextPrimaryStyle = androidx.compose.ui.text.TextStyle(
    fontSize = 16.sp,
    fontFamily = fontSemiBold,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center
)