package com.wcabral.feature.games

import androidx.lifecycle.viewModelScope
import com.wcabral.core.common.Result
import com.wcabral.core.common.asResult
import com.wcabral.core.data.repository.GamesRepository
import com.wcabral.core.data.repository.StoresRepository
import com.wcabral.core.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
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
            is GamesContract.Event.VideoSelection -> TODO()
            is GamesContract.Event.Retry -> fetchData()
            is GamesContract.Event.BackButtonClicked -> setEffect {
                GamesContract.Effect.Navigation.Back
            }
        }
    }

    private fun fetchGames() {
        viewModelScope.launch {
            gamesRepository.getAllGames()
                .asResult()
                .map { result ->
                    when (result) {
                        is Result.Loading -> {
                            setState { copy(isLoading = true, isError = false) }
                        }
                        is Result.Success -> {
                            setState { copy(isLoading = false, games = result.data) }
                            setEffect { GamesContract.Effect.DataWasLoaded }
                        }
                        is Result.Error -> {
                            // TODO: Handle the error cases
                            println("WELL: ${result.exception}")
                            setState { copy(isLoading = false, isError = true) }
                        }
                    }
                }.collect()
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
            }.catch {
                setState { copy(isLoading = false, isError = true) }
            }.collect()
        }
    }
}
