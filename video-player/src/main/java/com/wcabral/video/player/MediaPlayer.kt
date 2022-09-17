package com.wcabral.video.player

import androidx.media3.common.Player
import com.wcabral.video.player.model.Media

interface MediaPlayer {
    fun setup()
    fun setMedia(media: Media, playWhenReady: Boolean)
    fun setMediaItems(items: List<Media>, playWhenReady: Boolean)
    fun play()
    fun pause()
    fun stop()
    fun release()
    fun getPlayer(): Player?
    fun playWhenReady(play: Boolean = true)
    fun setMediaItemCallback(mediaItemCallback: MediaItemCallback)
}

fun interface MediaItemCallback {
    fun onMediaItemTransition(media: Media?, reason: Int)
}
