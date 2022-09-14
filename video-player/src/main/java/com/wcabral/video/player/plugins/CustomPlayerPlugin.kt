package com.wcabral.video.player.plugins

import androidx.media3.common.Player
import com.wcabral.video.player.MediaItemCallback
import com.wcabral.video.player.MediaPlayer
import com.wcabral.video.player.model.Media

internal class CustomPlayerPlugin: MediaPlayer {
    override fun setup() {}

    override fun setMedia(media: Media, playWhenReady: Boolean) {}

    override fun setMediaItems(items: List<Media>, playWhenReady: Boolean) {}

    override fun play() {}

    override fun pause() {}

    override fun stop() {}

    override fun release() {}

    override fun getPlayer(): Player? = null

    override fun playWhenReady(play: Boolean) {}

    override fun setMediaItemCallback(mediaItemCallback: MediaItemCallback) {}
}