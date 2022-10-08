package com.wcabral.feature.game.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.component.DesignSystemRoundedIconButton
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.icon.DesignSystemIcons
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.GameDetail
import com.wcabral.core.model.previewGameDetail

@Composable
fun GameDetailThumbnail(
    gameDetail: GameDetail,
    onNavigateToGameVideos: (gameId: Int) -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.clickable {
            if (gameDetail.moviesCount > 0) onNavigateToGameVideos(gameDetail.id)
        }
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(gameDetail.backgroundImage)
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

        if (gameDetail.moviesCount > 0) {
            DesignSystemRoundedIconButton(
                icon = DesignSystemIcons.PlayArrow,
                contentDescription = null,
                onClick = {
                    onNavigateToGameVideos(gameDetail.id)
                },
                modifier = Modifier
                    .padding(DesignSystemDimens.Padding.Medium)
            )
        }
    }
}

@Preview
@Composable
fun GameDetailThumbnailPreview() {
    DesignSystemTheme {
        GameDetailThumbnail(previewGameDetail) {}
    }
}
