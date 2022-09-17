package com.wcabral.feature.games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.Store
import com.wcabral.core.model.previewStores

@Composable
fun StoresList(items: List<Store>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(DesignSystemDimens.Padding.Medium),
    ) {
        items(items, key = { it.id }) { store ->
            StoresItem(store)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun StoresListPreview() {
    DesignSystemTheme {
        StoresList(previewStores)
    }
}