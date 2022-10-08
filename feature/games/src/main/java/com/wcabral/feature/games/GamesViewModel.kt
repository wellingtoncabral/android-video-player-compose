package com.wcabral.feature.games

import androidx.lifecycle.viewModelScope
import com.wcabral.core.data.repository.GamesRepository
import com.wcabral.core.data.repository.StoresRepository
import com.wcabral.core.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class GamesViewModel(
    private val gamesRepository: GamesRepository,
    private val storesRepository: StoresRepository,
) : BaseViewModel<GamesContract.Event, GamesContract.State, GamesContract.Effect>() {

    init { fetchData() }

    override fun setInitialState() = GamesContract.State(
        games = emptyList(),
        stores = emptyList(),
        isLoading = false,
        isError = false,
    )

    override fun handleEvents(event: GamesContract.Event) {
        when (event) {
            is GamesContract.Event.GameSelection -> {
                setEffect {
                    GamesContract.Effect.Navigation.ToGameDetail(event.gameId)
                }
            }
            is GamesContract.Event.BackButtonClicked -> {
                setEffect {
                    GamesContract.Effect.Navigation.Back
                }
            }
            is GamesContract.Event.Retry -> fetchData()
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            setState { copy(isLoading = true, isError = false) }
            combine(
                gamesRepository.getAllGames(),
                storesRepository.getAllStores(),
            ) { games, stores ->
                setState { copy(isLoading = false, isError = false, games = games, stores = stores) }
                setEffect { GamesContract.Effect.DataWasLoaded }
            }.catch {
                setState { copy(isLoading = false, isError = true) }
            }.collect()
        }
    }
}
