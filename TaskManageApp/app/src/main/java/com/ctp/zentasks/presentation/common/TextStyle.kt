package com.ctp.zentasks.presentation.common

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ctp.zentasks.R

val fontBold = FontFamily(Font(R.font.sf_bold))
val fontHeavy = FontFamily(Font(R.font.sf_heavy))
val fontLight = FontFamily(Font(R.font.sf_light))
val fontMedium = FontFamily(Font(R.font.sf_medium))
val fontRegular = FontFamily(Font(R.font.sf_regular))
val fontSemiBold = FontFamily(Font(R.font.sf_semibold))

// Start: fontBold
val h1TitleStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H1,
    fontFamily = fontBold,
    fontWeight = FontWeight.Bold
)
// End: fontBold

// Start: fontMedium
var h2TextStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H2,
    fontFamily = fontMedium,
    fontWeight = FontWeight.Normal
)
var h3TextStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H3,
    fontFamily = fontMedium,
    fontWeight = FontWeight.Normal
)
var h4TextStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H4,
    fontFamily = fontMedium,
    fontWeight = FontWeight.Normal
)
var h5TextStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H5,
    fontFamily = fontMedium,
    fontWeight = FontWeight.Normal
)
var h6TextStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H6,
    fontFamily = fontMedium,
    fontWeight = FontWeight.Normal
)
var h7TextStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H7,
    fontFamily = fontMedium,
    fontWeight = FontWeight.Normal
)
var h8TextStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H8,
    fontFamily = fontMedium,
    fontWeight = FontWeight.Normal
)
var h9TextStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H9,
    fontFamily = fontMedium,
    fontWeight = FontWeight.Normal
)
// End: fontMedium

// Start: fontLight
val h1TitleLightStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H1,
    fontFamily = fontLight,
    fontWeight = FontWeight.Normal
)
var h2TextLightStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H2,
    fontFamily = fontLight,
    fontWeight = FontWeight.Normal
)
var h3TextLightStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H3,
    fontFamily = fontLight,
    fontWeight = FontWeight.Normal
)
var h4TextLightStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H4,
    fontFamily = fontLight,
    fontWeight = FontWeight.Normal
)
var h5TextLightStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H5,
    fontFamily = fontLight,
    fontWeight = FontWeight.Normal
)
var h6TextLightStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H6,
    fontFamily = fontLight,
    fontWeight = FontWeight.Normal
)
var h7TextLightStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H7,
    fontFamily = fontLight,
    fontWeight = FontWeight.Normal
)
var h8TextLightStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H8,
    fontFamily = fontLight,
    fontWeight = FontWeight.Normal
)
var h9TextLightStyle = androidx.compose.ui.text.TextStyle(
    fontSize = TEXT_SIZE_H9,
    fontFamily = fontLight,
    fontWeight = FontWeight.Normal
)
// End: fontLight

var buttonTextPrimaryStyle = androidx.compose.ui.text.TextStyle(
    fontSize = 16.sp,
    fontFamily = fontSemiBold,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center
)