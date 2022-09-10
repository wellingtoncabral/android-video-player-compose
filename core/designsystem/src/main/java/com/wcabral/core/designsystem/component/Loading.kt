package com.wcabral.core.designsystem.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.wcabral.core.designsystem.theme.DesignSystemTheme

@Composable
fun DesignSystemLoading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Preview("night mode", uiMode = UI_MODE_NIGHT_YES)
@Preview("light mode")
@Composable
fun DesignSystemLoadingPreview() {
    DesignSystemTheme {
        DesignSystemLoading()
    }
}