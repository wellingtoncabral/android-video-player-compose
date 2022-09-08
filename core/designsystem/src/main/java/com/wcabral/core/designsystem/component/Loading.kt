package com.wcabral.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wcabral.core.designsystem.theme.VideoPlayerComposeTheme

@Composable
fun DesignSystemLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DesignSystemLoadingPreview() {
    VideoPlayerComposeTheme {
        DesignSystemLoading()
    }
}