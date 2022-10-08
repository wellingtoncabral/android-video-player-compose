package com.wcabral.core.designsystem.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
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
        val SmallIconSize = 15.dp
        val IconSize = 35.dp
    }
}
