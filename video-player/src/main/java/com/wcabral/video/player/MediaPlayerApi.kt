package com.wcabral.video.player

import android.content.Context
import com.wcabral.video.player.plugins.CustomPlayerPlugin
import com.wcabral.video.player.plugins.ExoMediaPlayerPlugin

sealed class MediaPlayerApi {
    data class ExoPlayer(val context: Context) : MediaPlayerApi()
    object CustomPlayer : MediaPlayerApi()
}

fun createMediaPlayer(mediaPlayerApi: MediaPlayerApi): MediaPlayer = when (mediaPlayerApi) {
    is MediaPlayerApi.ExoPlayer -> ExoMediaPlayerPlugin(mediaPlayerApi.context).also { it.setup() }
    is MediaPlayerApi.CustomPlayer -> CustomPlayerPlugin().also { it.setup() }
}
