package com.wcabral.feature.store.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.Store
import com.wcabral.core.model.previewStore

@Composable
fun StoreDetailThumbnail(store: Store) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(store.backgroundImage)
            .crossfade(true)
            .build(),
        loading = {
            DesignSystemLoading(modifier = Modifier.fillMaxSize())
        },
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
    )
}

@Preview
@Composable
fun StoreDetailThumbnailPreview() {
    DesignSystemTheme {
        StoreDetailThumbnail(previewStore)
    }
}
