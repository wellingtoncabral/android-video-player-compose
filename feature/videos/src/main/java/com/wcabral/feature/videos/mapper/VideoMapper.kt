package com.wcabral.feature.videos.mapper

import android.net.Uri
import com.wcabral.core.model.Video
import com.wcabral.video.player.model.Media

fun Video.toMediaPlugin() = Media(
    id = id.toString(),
    uri = Uri.parse(mediaMax)
)

fun List<Video>.toMediaPlugin() = map { it.toMediaPlugin() }
