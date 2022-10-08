package com.wcabral.feature.games

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.wcabral.core.designsystem.component.DesignSystemCard
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.Store
import com.wcabral.core.model.previewStore

@Composable
fun StoresItem(store: Store) {
    DesignSystemCard {
        Column(modifier = Modifier.width(150.dp)) {
            StoresItemHeader(store)
            StoresItemDetails(store)
        }
    }
}

@Composable
fun StoresItemHeader(store: Store) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(store.imageBackground)
            .crossfade(true)
            .build(),
        loading = {
            DesignSystemLoading()
        },
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    )
}

@Composable
fun StoresItemDetails(store: Store) {
    Text(
        text = store.name,
        modifier = Modifier
            .fillMaxWidth()
            .padding(DesignSystemDimens.Padding.ScreenAll),
        maxLines = 1,
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
    )
}

@Preview(showBackground = true)
@Composable
fun StoreItemPreview() {
    DesignSystemTheme {
        StoresItem(store = previewStore)
    }
}
