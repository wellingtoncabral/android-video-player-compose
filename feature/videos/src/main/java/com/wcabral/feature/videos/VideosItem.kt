package com.wcabral.feature.videos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.wcabral.core.designsystem.component.DesignSystemCard
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.component.DesignSystemRoundedIconButton
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.icon.DesignSystemIcons
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.Video
import com.wcabral.core.model.previewVideo

@Composable
fun VideosItem(video: Video, isPlaying: Boolean) {
    DesignSystemCard(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Box(contentAlignment = Alignment.Center) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(video.preview)
                        .crossfade(true)
                        .build(),
                    loading = {
                        DesignSystemLoading()
                    },
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .shadow(DesignSystemDimens.Shadow)
                        .width(130.dp)
                        .height(130.dp)
                )

                if (isPlaying) {
                    DesignSystemRoundedIconButton(
                        icon = DesignSystemIcons.PlayArrow,
                        contentDescription = null,
                        onClick = {},
                    )
                }
            }
            Text(
                text = video.name,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(DesignSystemDimens.Padding.ScreenAll)
            )
        }
    }
}

@Preview("playing", showBackground = true)
@Composable
fun VideoItemPlayingPreview() {
    DesignSystemTheme {
        VideosItem(video = previewVideo, isPlaying = true)
    }
}

@Preview("default", showBackground = true)
@Composable
fun VideoItemDefaultPreview() {
    DesignSystemTheme {
        VideosItem(video = previewVideo, isPlaying = false)
    }
}
