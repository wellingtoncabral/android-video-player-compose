package com.wcabral.feature.videos

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.theme.DesignSystemTheme

@Composable
fun VideosLoading() {
    DesignSystemLoading()
}

@Preview("night mode", uiMode = UI_MODE_NIGHT_YES)
@Preview("light mode")
@Composable
fun VideosLoadingPreview() {
    DesignSystemTheme {
        VideosLoading()
    }
}
