package com.wcabral.feature.videos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wcabral.core.model.Video
import com.wcabral.video.player.MediaPlayer
import com.wcabral.video.player.ui.VideoPlayerView

@Composable
fun VideosView(
    mediaPlayer: MediaPlayer?,
    currentMediaId: String?,
    videos: List<Video>
) {
    Column(modifier = Modifier.systemBarsPadding()) {
        VideoPlayerView(
            mediaPlayer = mediaPlayer,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        )
        VideosList(currentMediaId = currentMediaId, videos = videos)
    }
}
