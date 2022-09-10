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
    primary = DesignSystemColors.Dark,
    primaryVariant = DesignSystemColors.Dark,
    secondary = DesignSystemColors.Dark,
    background = DesignSystemColors.Dark,
    surface = DesignSystemColors.Dark,
    onPrimary = DesignSystemColors.Light,
    onSecondary = DesignSystemColors.Light,
    onBackground = DesignSystemColors.Light,
    onSurface = DesignSystemColors.Light,
)

private val LightColorPalette = lightColors(
    primary = DesignSystemColors.Light,
    primaryVariant = DesignSystemColors.Light,
    secondary = DesignSystemColors.Light,
    background = DesignSystemColors.Light,
    surface = DesignSystemColors.Light,
    onPrimary = DesignSystemColors.Dark,
    onSecondary = DesignSystemColors.Dark,
    onBackground = DesignSystemColors.Dark,
    onSurface = DesignSystemColors.Dark,
)

@Composable
fun DesignSystemTheme(
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