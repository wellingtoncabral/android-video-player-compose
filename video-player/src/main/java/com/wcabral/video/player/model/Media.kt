package com.wcabral.video.player.model

import android.net.Uri
import androidx.media3.common.MediaItem

data class Media(val id: String, val uri: Uri?)

fun Media.toMediaItem() = MediaItem.Builder()
    .setMediaId(id)
    .setUri(uri)
    .build()

fun List<Media>.toMediaItem() = map { it.toMediaItem() }

fun MediaItem.toMedia() = Media(
    id = this.mediaId,
    uri = null,
)
