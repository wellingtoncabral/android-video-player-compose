package com.wcabral.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColorScheme(
    primary = DesignSystemColors.Dark,
    primaryContainer = DesignSystemColors.Dark,
    secondary = DesignSystemColors.Light,
    tertiary = DesignSystemColors.Accent,
    background = DesignSystemColors.Dark,
    surface = DesignSystemColors.DarkSurface,
    onPrimary = DesignSystemColors.Light,
    onSecondary = DesignSystemColors.Light,
    onTertiary = DesignSystemColors.Black,
    onBackground = DesignSystemColors.Light,
    onSurface = DesignSystemColors.Light,
)

private val LightColorPalette = lightColorScheme(
    primary = DesignSystemColors.Light,
    primaryContainer = DesignSystemColors.Light,
    secondary = DesignSystemColors.Dark,
    tertiary = DesignSystemColors.Accent,
    background = DesignSystemColors.Light,
    surface = DesignSystemColors.LightSurface,
    onPrimary = DesignSystemColors.Dark,
    onSecondary = DesignSystemColors.Dark,
    onTertiary = DesignSystemColors.White,
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
        colorScheme = colors,
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