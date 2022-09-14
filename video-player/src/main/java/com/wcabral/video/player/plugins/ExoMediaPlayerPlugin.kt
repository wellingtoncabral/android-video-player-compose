package com.wcabral.video.player.plugins

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.wcabral.video.player.MediaItemCallback
import com.wcabral.video.player.MediaPlayer
import com.wcabral.video.player.model.Media
import com.wcabral.video.player.model.toMedia
import com.wcabral.video.player.model.toMediaItem

internal class ExoMediaPlayerPlugin(
    private val context: Context
) : MediaPlayer {

    private var exoPlayer: ExoPlayer? = null
    private var mediaItemCallback: MediaItemCallback? = null

    private val defaultDataSourceFactory = DefaultDataSource.Factory(context)
    private val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(context, defaultDataSourceFactory)

    private val playerListener = object : Player.Listener {
        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            super.onMediaItemTransition(mediaItem, reason)
            mediaItemCallback?.onMediaItemTransition(mediaItem?.toMedia(), reason)
        }
    }

    override fun setup() {
        exoPlayer = ExoPlayer.Builder(context)
            .build()
            .apply { addListener(playerListener) }
    }

    override fun setMedia(media: Media, playWhenReady: Boolean) {
        val source = ProgressiveMediaSource
            .Factory(dataSourceFactory)
            .createMediaSource(media.toMediaItem())

        exoPlayer?.apply {
            setMediaSource(source)
            prepare()
            this.playWhenReady = playWhenReady
        }
    }

    override fun setMediaItems(items: List<Media>, playWhenReady: Boolean) {
        exoPlayer?.apply {
            setMediaItems(items.toMediaItem())
            prepare()
            this.playWhenReady = playWhenReady
        }
    }

    override fun play() {
        exoPlayer?.play()
    }

    override fun pause() {
        exoPlayer?.pause()
    }

    override fun stop() {
        exoPlayer?.stop()
    }

    override fun release() {
        exoPlayer?.release()
    }

    override fun getPlayer(): Player? = exoPlayer

    override fun playWhenReady(play: Boolean) {
        exoPlayer?.playWhenReady = play
    }

    override fun setMediaItemCallback(mediaItemCallback: MediaItemCallback) {
        this.mediaItemCallback = mediaItemCallback
    }
}
