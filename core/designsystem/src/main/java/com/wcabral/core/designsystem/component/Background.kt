package com.wcabral.core.designsystem.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wcabral.core.designsystem.theme.DesignSystemTheme

@Composable
fun DesignSystemBackground(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.background,
    content: @Composable () -> Unit,
) {
    Surface(
        color = color,
        modifier = modifier,
    ) {
        content()
    }
}

@Preview("night mode", uiMode = UI_MODE_NIGHT_YES)
@Preview("light mode")
@Composable
fun DesignSystemBackgroundPreview() {
    DesignSystemTheme {
        DesignSystemBackground(modifier = Modifier.size(100.dp)) {}
    }
}
