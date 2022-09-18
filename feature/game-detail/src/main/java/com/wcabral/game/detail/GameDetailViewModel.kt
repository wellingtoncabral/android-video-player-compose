package com.wcabral.game.detail

import androidx.lifecycle.viewModelScope
import com.wcabral.core.common.Result
import com.wcabral.core.common.asResult
import com.wcabral.core.data.repository.GamesRepository
import com.wcabral.core.ui.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class GameDetailViewModel(
    private val gameId: Int,
    private val gamesRepository: GamesRepository,
) : BaseViewModel<GameDetailContract.Event, GameDetailContract.State, GameDetailContract.Effect>() {

    init {
        fetchGameDetail()
    }

    override fun setInitialState() = GameDetailContract.State(
        gameDetail = null,
        isLoading = false,
        isError = false,
    )

    override fun handleEvents(event: GameDetailContract.Event) {
        when (event) {
            is GameDetailContract.Event.Retry -> fetchGameDetail()
            is GameDetailContract.Event.BackButtonClicked -> setEffect {
                GameDetailContract.Effect.Navigation.Back
            }
            is GameDetailContract.Event.VideosClicked -> setEffect {
                GameDetailContract.Effect.Navigation.ToGameVideos(event.gameId)
            }
        }
    }

    private fun fetchGameDetail() {
        viewModelScope.launch {
            gamesRepository.getGameDetails(gameId)
                .asResult()
                .map { result ->
                    when (result) {
                        is Result.Loading -> {
                            setState { copy(isLoading = true, isError = false) }
                        }
                        is Result.Success -> {
                            setState { copy(isLoading = false, gameDetail = result.data) }
                        }
                        is Result.Error -> {
                            setState { copy(isLoading = false, isError = true) }
                        }
                    }
                }.collect()
        }
    }
}
