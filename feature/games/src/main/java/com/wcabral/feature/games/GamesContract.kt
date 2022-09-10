package com.wcabral.feature.games

import com.wcabral.core.model.Game
import com.wcabral.core.model.Store
import com.wcabral.core.ui.ViewEvent
import com.wcabral.core.ui.ViewSideEffect
import com.wcabral.core.ui.ViewState

class GamesContract {

    sealed class Event : ViewEvent {
        object Retry: Event()
        object BackButtonClicked: Event()
        data class VideoSelection(val videoId: Int) : Event()
    }

    data class State(
        val games: List<Game>,
        val stores: List<Store>,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation: Effect() {
            object Back : Navigation()
        }
    }
}