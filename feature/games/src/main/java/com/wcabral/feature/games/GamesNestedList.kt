package com.wcabral.feature.games

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.wcabral.core.designsystem.component.DesignSystemHeader
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.Game
import com.wcabral.core.model.Store
import com.wcabral.core.model.previewGames
import com.wcabral.core.model.previewStores

@Composable
fun GamesNestedList(list: List<Game>, stores: List<Store>) {
    val state = rememberLazyGridState()
    val columns = when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> 2
        else -> 1
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        state = state,
        verticalArrangement = Arrangement.spacedBy(DesignSystemDimens.Padding.Medium),
        horizontalArrangement = Arrangement.spacedBy(DesignSystemDimens.Padding.Small),
    ) {
        item {
            DesignSystemHeader(
                titleRes = R.string.stores,
                modifier = Modifier.padding(vertical = DesignSystemDimens.Padding.ScreenVertical)
            )
        }

        item {
            StoresList(items = stores)
        }

        item {
            DesignSystemHeader(
                titleRes = R.string.recommended_for_you,
                modifier = Modifier.padding(vertical = DesignSystemDimens.Padding.ScreenVertical)
            )
        }

        items(list, key = { it.id }) { item ->
            GamesItem(game = item)
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
fun VideosGameListPreview() {
    DesignSystemTheme {
        GamesNestedList(list = previewGames, stores = previewStores)
    }
}
