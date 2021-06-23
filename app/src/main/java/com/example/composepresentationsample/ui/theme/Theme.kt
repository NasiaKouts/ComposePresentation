package com.example.composepresentationsample.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Purple600,
    primaryVariant = Purple800,
    onPrimary = Color.White,
    secondary = Cyan200,
    secondaryVariant = Purple800,
    background = Color.White,
    onSecondary = Color.White,
    error = Red800
)

private val DarkColorPalette = darkColors(
    primary = Purple300,
    primaryVariant = Purple600,
    onPrimary = Color.White,
    secondary = Purple300,
    secondaryVariant = Purple600,
    background = Color.DarkGray,
    onSecondary = Color.White,
    error = Red800
)

@Composable
fun ComposePresentationSampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
            colors = colors,
            content = content
    )
}