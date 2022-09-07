package com.wcabral.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Dark,
    primaryVariant = Dark,
    secondary = Dark,
    background = Dark,
    surface = Dark,
    onPrimary = Light,
    onSecondary = Light,
    onBackground = Light,
    onSurface = Light,
)

private val LightColorPalette = lightColors(
    primary = Light,
    primaryVariant = Light,
    secondary = Light,
    background = Light,
    surface = Light,
    onPrimary = Dark,
    onSecondary = Dark,
    onBackground = Dark,
    onSurface = Dark,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun VideoPlayerComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    // Update the system bars to be translucent
    SystemBarTranslucent(colors.background, !darkTheme)

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun SystemBarTranslucent(background: Color, darkIcons: Boolean) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            background,
            darkIcons
        )
    }
}