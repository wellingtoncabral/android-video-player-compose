package com.wcabral.feature.videos

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

class VideosViewModel(
    private val gamesRepository: GamesRepository,
    private val storesRepository: StoresRepository,
) : BaseViewModel<VideosContract.Event, VideosContract.State, VideosContract.Effect>() {

    init { fetchData() }

    override fun setInitialState() = VideosContract.State(
        videos = emptyList(),
        stores = emptyList(),
        isLoading = false,
        isError = false,
    )

    override fun handleEvents(event: VideosContract.Event) {
        when (event) {
            is VideosContract.Event.VideoSelection -> TODO()
            is VideosContract.Event.Retry -> fetchData()
            is VideosContract.Event.BackButtonClicked -> setEffect {
                VideosContract.Effect.Navigation.Back
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
                            setState { copy(isLoading = false, videos = result.data) }
                            setEffect { VideosContract.Effect.DataWasLoaded }
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
                setState { copy(isLoading = false, isError = false, videos = games, stores = stores) }
            }.catch {
                setState { copy(isLoading = false, isError = true) }
            }.collect()
        }
    }
}
