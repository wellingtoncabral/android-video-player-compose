package com.wcabral.video.player.ui

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.wcabral.video.player.MediaPlayer

@Composable
fun VideoPlayerView(
    mediaPlayer: MediaPlayer?,
    modifier: Modifier = Modifier,
    userController: Boolean = true,
    resizeMode: Int = AspectRatioFrameLayout.RESIZE_MODE_FILL,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .testTag("VideoPlayerParent")
    ) {
        AndroidView(factory = {
            PlayerView(context).apply {
                hideController()
                useController = userController
                setResizeMode(resizeMode)
                player = mediaPlayer?.getPlayer()
                layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            }
        })
    }
}