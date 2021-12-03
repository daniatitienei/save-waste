package com.rozatorii_bulbucasi.savewaste.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun SaveWasteTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        content = content,
        colors = lightColors,
        typography = SaveWasteTypography,
        shapes = Shapes
    )
}

private val lightColors = lightColors(
    primary = Color.White,
    primaryVariant = Color.LightGray,
    onPrimary = Color.Black,
    secondary = Green400,
    secondaryVariant = Green200,
    onSecondary = Color.White,
)