package com.wcabral.core.designsystem.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.wcabral.core.designsystem.theme.DesignSystemTheme

@Composable
fun DesignSystemBackground(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.background,
    content: @Composable () -> Unit,
) {
    Surface(
        color = color,
        modifier = modifier.fillMaxSize(),
    ) {
        content()
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun DesignSystemBackgroundPreview() {
    DesignSystemTheme {
        DesignSystemBackground {}
    }
}
