package com.wcabral.feature.game.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.wcabral.core.designsystem.component.DesignSystemChip
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.Platform
import com.wcabral.core.model.previewPlatforms

@Composable
fun GameDetailPlatforms(platforms: List<Platform>) {
    FlowRow(
        mainAxisSpacing = 2.dp,
    ) {
        platforms.forEach { platform ->
            DesignSystemChip(onClick = {  }) { Text(text = platform.name) }
        }
    }
}

@Preview
@Composable
fun GameDetailPlatformsPreview() {
    DesignSystemTheme {
        GameDetailPlatforms(previewPlatforms)
    }
}
