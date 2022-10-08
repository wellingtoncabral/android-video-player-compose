package com.wcabral.feature.store.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wcabral.core.designsystem.component.DesignSystemBackground
import com.wcabral.core.designsystem.component.DesignSystemHeader
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.Store
import com.wcabral.core.model.previewStore
import com.wcabral.core.ui.previews.LightAndNightPreviews

@Composable
fun StoreDetailView(store: Store) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
    ) {
        StoreDetailThumbnail(store)
        Spacer(modifier = Modifier.size(DesignSystemDimens.Padding.Medium))
        Column(modifier = Modifier.padding(DesignSystemDimens.Padding.ScreenAll)) {
            DesignSystemHeader(title = store.name)
            Spacer(modifier = Modifier.size(DesignSystemDimens.Padding.Small))
            store.description?.let {
                Text(
                    text = it
                )
            }
        }
    }
}

@LightAndNightPreviews
@Composable
fun StoreDetailViewPreview() {
    DesignSystemTheme {
        DesignSystemBackground {
            StoreDetailView(store = previewStore)
        }
    }
}
