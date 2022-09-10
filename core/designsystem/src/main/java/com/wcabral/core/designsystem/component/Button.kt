package com.wcabral.core.designsystem.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wcabral.core.designsystem.theme.DesignSystemColors
import com.wcabral.core.designsystem.theme.DesignSystemTheme

@Composable
fun DesignSystemButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
    ) {
        content()
    }
}

@Preview("dark mode", uiMode = UI_MODE_NIGHT_YES)
@Preview("light mode")
@Composable
fun DesignSystemButtonPreview() {
    DesignSystemTheme {
        DesignSystemButton(
            onClick = {}
        ) {
            Text(text = "Button title")
        }
    }
}

object DesignSystemButtonDefaults {
    object RoundedIconButton {
        const val Alpha = 0.6f
        val Size = 55.dp
        val Background = DesignSystemColors.Black
        val IconColor = DesignSystemColors.White
        val IconSize = 35.dp
    }
}
