package com.rozatorii_bulbucasi.savewaste.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rozatorii_bulbucasi.savewaste.R

private val LouisGeorgeCafe = FontFamily(
    Font(R.font.louis_george_cafe),
    Font(R.font.louis_george_cafe_bold, weight = FontWeight.Bold),
    Font(R.font.louis_george_cafe_light, weight = FontWeight.Light)
)

val SaveWasteTypography = Typography(
    h4 = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    caption = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    )
)