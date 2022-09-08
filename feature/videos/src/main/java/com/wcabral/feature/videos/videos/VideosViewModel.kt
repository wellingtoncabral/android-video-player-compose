package com.wcabral.feature.videos.videos

import androidx.lifecycle.viewModelScope
import com.wcabral.core.common.Result
import com.wcabral.core.common.asResult
import com.wcabral.core.data.repository.GamesRepository
import com.wcabral.core.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class VideosViewModel(
    private val gamesRepository: GamesRepository
) : BaseViewModel<VideosContract.Event, VideosContract.State, VideosContract.Effect>() {

    init { getGames() }

    override fun setInitialState() = VideosContract.State(
        videos = emptyList(),
        isLoading = false,
        isError = false,
    )

    override fun handleEvents(event: VideosContract.Event) {
        when (event) {
            is VideosContract.Event.VideoSelection -> TODO()
        }
    }

    private fun getGames() {
        viewModelScope.launch {
            with(Dispatchers.IO) {
                gamesRepository.getAllGames()
                    .asResult()
                    .map { result ->
                        when (result) {
                            is Result.Loading -> setState { copy(isLoading = true, isError = false) }
                            is Result.Success -> setState { copy(isLoading = false, videos = result.data) }
                            is Result.Error -> {
                                println("WELL: ${result.exception}")
                                setState { copy(isLoading = false, isError = true) }
                            }
                        }
                    }.collect()
            }
        }
    }


}