package com.wcabral.feature.videos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.wcabral.core.designsystem.component.DesignSystemCard
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.Store
import com.wcabral.core.model.previewStores

@Composable
fun VideosStoreList(items: List<Store>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(DesignSystemDimens.Padding.ExtraSmall),
    ) {
        items(items, key = { it.id }) {
            DesignSystemCard {
                Box(modifier = Modifier
                    .size(150.dp)
                ) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.imageBackground)
                            .crossfade(true)
                            .build(),
                        loading = {
                            DesignSystemLoading()
                        },
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .shadow(16.dp)
                    )
                    Text(
                        text = it.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(0.7f)
                            .background(MaterialTheme.colors.secondary)
                            .align(Alignment.BottomStart)
                            .padding(DesignSystemDimens.Padding.Medium),
                        color = Color.White,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun VideosStoreListPreview() {
    DesignSystemTheme {
        VideosStoreList(previewStores)
    }
}