package com.wcabral.feature.games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.Store
import com.wcabral.core.model.previewStores
import com.wcabral.core.ui.previews.LightAndNightPreviews

@Composable
fun StoresList(
    items: List<Store>,
    onItemClick: (Store) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(DesignSystemDimens.Padding.Medium),
    ) {
        items(items, key = { it.id }) { store ->
            StoresItem(store, onClick = onItemClick)
        }
    }
}

@LightAndNightPreviews
@Composable
fun StoresListPreview() {
    DesignSystemTheme {
        StoresList(previewStores, onItemClick = {})
    }
}