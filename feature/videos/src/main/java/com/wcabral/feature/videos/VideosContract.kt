package com.wcabral.feature.videos

import com.wcabral.core.model.Video
import com.wcabral.core.ui.ViewEvent
import com.wcabral.core.ui.ViewSideEffect
import com.wcabral.core.ui.ViewState
import com.wcabral.video.player.MediaPlayer
import com.wcabral.video.player.model.Media

class VideosContract {

    sealed class Event : ViewEvent {
        object Retry: Event()
        object BackButtonClicked: Event()
        data class VideoSelection(val media: Media) : Event()
    }

    data class State(
        val mediaPlayer: MediaPlayer?,
        val videos: List<Video>,
        val currentMediaId: String?,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation: Effect() {
            object Back : Navigation()
        }
    }
}