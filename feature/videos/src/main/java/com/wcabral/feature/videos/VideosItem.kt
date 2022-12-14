package com.wcabral.feature.videos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.wcabral.core.designsystem.component.DesignSystemButtonDefaults
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
    val border: BorderStroke?
    val background: Color
    if (isPlaying) {
        background = MaterialTheme.colorScheme.surface
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.tertiary)
    } else {
        background = MaterialTheme.colorScheme.background
        border = null
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(DesignSystemDimens.Padding.Medium),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {  }
            .fillMaxWidth()
            .wrapContentHeight()
            .background(background)
            .padding(
                horizontal = DesignSystemDimens.Padding.ScreenHorizontal,
                vertical = DesignSystemDimens.Padding.Small
            )
    ) {
        DesignSystemCard(
            border = border,
            modifier = Modifier
                .width(90.dp)
                .height(75.dp)
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(video.preview)
                    .crossfade(true)
                    .build(),
                loading = {
                    DesignSystemLoading(modifier = Modifier.fillMaxSize())
                },
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )
        }
        Text(
            text = video.name,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 2,
            modifier = Modifier
                .weight(1f)
        )

        DesignSystemRoundedIconButton(
            icon = DesignSystemIcons.PlayArrow,
            contentDescription = null,
            alpha = 1f,
            size = DesignSystemButtonDefaults.RoundedIconButton.SmallSize,
            iconSize = DesignSystemButtonDefaults.RoundedIconButton.SmallIconSize,
            onClick = {},
        )
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
