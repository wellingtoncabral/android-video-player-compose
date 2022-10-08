package com.wcabral.feature.game.detail

import com.wcabral.core.model.GameDetail
import com.wcabral.core.ui.ViewEvent
import com.wcabral.core.ui.ViewSideEffect
import com.wcabral.core.ui.ViewState

class GameDetailContract {

    sealed class Event : ViewEvent {
        object Retry: Event()
        object BackButtonClicked: Event()
        data class VideosClicked(val gameId: Int) : Event()
    }

    data class State(
        val gameDetail: GameDetail?,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation: Effect() {
            object Back : Navigation()
            data class ToGameVideos(val gameId: Int): Navigation()
        }
    }
}