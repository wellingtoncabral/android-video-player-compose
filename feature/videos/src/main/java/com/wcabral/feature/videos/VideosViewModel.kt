package com.wcabral.feature.videos

import androidx.lifecycle.viewModelScope
import com.wcabral.core.common.Result
import com.wcabral.core.common.asResult
import com.wcabral.core.data.repository.GamesRepository
import com.wcabral.core.model.Video
import com.wcabral.core.ui.BaseViewModel
import com.wcabral.feature.videos.mapper.toMediaPlugin
import com.wcabral.video.player.MediaPlayer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class VideosViewModel(
    private val gameId: Int,
    private val gamesRepository: GamesRepository,
    private val mediaPlayer: MediaPlayer,
) : BaseViewModel<VideosContract.Event, VideosContract.State, VideosContract.Effect>() {

    init {
        fetchMovies()
    }

    override fun setInitialState() = VideosContract.State(
        mediaPlayer = null,
        videos = emptyList(),
        currentMediaId = null,
        isLoading = false,
        isError = false,
    )

    override fun handleEvents(event: VideosContract.Event) {
        when (event) {
            is VideosContract.Event.VideoSelection -> TODO()
            is VideosContract.Event.Retry -> fetchMovies()
            is VideosContract.Event.BackButtonClicked -> setEffect {
                VideosContract.Effect.Navigation.Back
            }
        }
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            gamesRepository.getAllMovies(gameId)
                .asResult()
                .map { result ->
                    when (result) {
                        is Result.Loading -> {
                            setState { copy(isLoading = true, isError = false) }
                        }
                        is Result.Success -> {
                            setState { copy(isLoading = false, videos = result.data, mediaPlayer = this@VideosViewModel.mediaPlayer) }
                            initMediaPlayer(result.data)
                        }
                        is Result.Error -> {
                            setState { copy(isLoading = false, isError = true) }
                        }
                    }
                }.collect()
        }
    }

    private fun initMediaPlayer(items: List<Video>) {
        if (items.isNotEmpty()) {
            mediaPlayer.apply {
                // Init callbacks
                setMediaItemCallback { media, _ ->
                    setState { copy(currentMediaId = media?.id) }
                }

                // Set media items
                setMediaItems(
                    items = items.toMediaPlugin(),
                    playWhenReady = true,
                )
            }
        }
    }

    override fun onCleared() {
        mediaPlayer.release()
        super.onCleared()
    }
}
