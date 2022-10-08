package com.wcabral.feature.videos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wcabral.core.designsystem.component.DesignSystemBackground
import com.wcabral.core.designsystem.component.DesignSystemHeader
import com.wcabral.core.designsystem.component.DesignSystemPrimaryButton
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.Video
import com.wcabral.core.model.previewVideos
import com.wcabral.video.player.MediaPlayer
import com.wcabral.video.player.ui.VideoPlayerView

@Composable
fun VideosView(
    mediaPlayer: MediaPlayer?,
    currentMediaId: String?,
    videos: List<Video>,
    onBackClick: () -> Unit,
) {
    Column(modifier = Modifier
        .systemBarsPadding()
        .fillMaxSize()
    ) {
        if (videos.isEmpty()) {
            NoItemsAvailable(onBackClick)
        } else {
            VideoPlayerView(
                mediaPlayer = mediaPlayer,
                onNavigateBack = onBackClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            )
            VideosList(currentMediaId = currentMediaId, videos = videos)
        }
    }
}

@Composable
fun NoItemsAvailable(
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DesignSystemHeader(titleRes = R.string.no_items_available)
        Spacer(modifier = Modifier.size(DesignSystemDimens.Padding.Small))
        DesignSystemPrimaryButton(onClick = onBackClick) {
            Text(text = stringResource(id = R.string.back))
        }
    }
}

@Preview("populated")
@Composable
fun VideosViewPreview() {
    DesignSystemTheme {
        VideosView(
            mediaPlayer = null,
            currentMediaId = null,
            videos = previewVideos,
            onBackClick = {},
        )
    }
}

@Preview("no items available")
@Composable
fun VideosViewNoItemsAvailablePreview() {
    DesignSystemTheme {
        DesignSystemBackground {
            VideosView(
                mediaPlayer = null,
                currentMediaId = null,
                videos = emptyList(),
                onBackClick = {}
            )
        }
    }
}

