package com.wcabral.feature.videos

import android.content.res.Configuration
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.Game
import com.wcabral.core.model.previewGames

@Composable
fun VideosList(list: List<Game>) {
    val columns = when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> 2
        else -> 1
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        verticalArrangement = Arrangement.spacedBy(DesignSystemDimens.Padding.Medium),
        horizontalArrangement = Arrangement.spacedBy(DesignSystemDimens.Padding.Small),
    ) {
        items(list, key = { it.id }) { item ->
            VideosItem(item = item)
        }
    }
}

@Preview(name = "phone", device = Devices.PHONE, showBackground = true)
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480", showBackground = true)
@Preview(name = "foldable", device = Devices.FOLDABLE, showBackground = true)
@Preview(name = "tablet", device = Devices.TABLET, showBackground = true)
@Preview("light mode", showBackground = true)
@Preview("dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun VideosListPreview() {
    DesignSystemTheme {
        VideosList(list = previewGames)
    }
}
