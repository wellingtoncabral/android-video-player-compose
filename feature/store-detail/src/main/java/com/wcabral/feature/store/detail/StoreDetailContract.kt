package com.wcabral.feature.store.detail

import com.wcabral.core.model.Store
import com.wcabral.core.ui.ViewEvent
import com.wcabral.core.ui.ViewSideEffect
import com.wcabral.core.ui.ViewState

class StoreDetailContract {

    sealed class Event : ViewEvent {
        object Retry: Event()
        object BackButtonClicked: Event()
    }

    data class State(
        val store: Store?,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation: Effect() {
            object Back : Navigation()
        }
    }
}