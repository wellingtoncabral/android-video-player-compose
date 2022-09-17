package com.wcabral.core.designsystem.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wcabral.core.designsystem.theme.DesignSystemColors
import com.wcabral.core.designsystem.theme.DesignSystemTheme

@Composable
fun DesignSystemPrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = DesignSystemColors.Accent,
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        content()
    }
}

@Preview("dark mode", uiMode = UI_MODE_NIGHT_YES)
@Preview("light mode")
@Composable
fun DesignSystemButtonPreview() {
    DesignSystemTheme {
        DesignSystemPrimaryButton(
            onClick = {}
        ) {
            Text(text = "Button title")
        }
    }
}

object DesignSystemButtonDefaults {
    object RoundedIconButton {
        const val Alpha = 1f
        val SmallSize = 30.dp
        val Size = 55.dp
        val Background = DesignSystemColors.Accent
        val IconColor = DesignSystemColors.White
        val SmallIconSize = 15.dp
        val IconSize = 35.dp
    }
}
