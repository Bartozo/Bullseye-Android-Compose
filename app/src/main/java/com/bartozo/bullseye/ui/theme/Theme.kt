package com.bartozo.bullseye.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = rwGreen,
    primaryVariant = rwGreenDark,
    secondary = rwRed
)

private val LightColorPalette = lightColors(
    primary = rwGreen,
    primaryVariant = rwGreenDark,
    secondary = rwRed
)

@Composable
fun BullseyeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}