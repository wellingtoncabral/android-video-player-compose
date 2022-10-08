package com.wcabral.feature.games

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.wcabral.core.designsystem.component.DesignSystemBackground
import com.wcabral.core.designsystem.component.DesignSystemHeader
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.Game
import com.wcabral.core.model.Store
import com.wcabral.core.model.previewGames
import com.wcabral.core.model.previewStores
import com.wcabral.core.ui.previews.DevicePreviews
import com.wcabral.core.ui.previews.LightAndNightPreviews

@Composable
fun GamesNestedList(
    list: List<Game>,
    stores: List<Store>,
    onGameClick: (Game) -> Unit,
) {
    val state = rememberLazyGridState()
    val columns = when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> 2
        else -> 1
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        state = state,
        contentPadding = PaddingValues(DesignSystemDimens.Padding.ScreenAll),
        verticalArrangement = Arrangement.spacedBy(DesignSystemDimens.Padding.Medium),
        horizontalArrangement = Arrangement.spacedBy(DesignSystemDimens.Padding.Small),
    ) {
        item(span = { GridItemSpan(columns) }) {
            DesignSystemHeader(
                titleRes = R.string.stores,
                modifier = Modifier.padding(top = DesignSystemDimens.Padding.ScreenVertical)
            )
        }

        item(span = { GridItemSpan(columns) }) {
            StoresList(items = stores)
        }

        item(span = { GridItemSpan(columns) }) {
            DesignSystemHeader(
                titleRes = R.string.recommended_for_you,
                modifier = Modifier.padding(top = DesignSystemDimens.Padding.ScreenVertical)
            )
        }

        items(list, key = { it.id }) { item ->
            GamesItem(game = item, onClick = { onGameClick(it) })
        }
    }
}

@DevicePreviews
@LightAndNightPreviews
@Composable
fun VideosGameListPreview() {
    DesignSystemTheme {
        DesignSystemBackground {
            GamesNestedList(
                list = previewGames,
                stores = previewStores,
                onGameClick = {},
            )
        }
    }
}
