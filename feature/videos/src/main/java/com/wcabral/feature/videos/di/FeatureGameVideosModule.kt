package com.wcabral.feature.videos.di

import com.wcabral.feature.videos.VideosViewModel
import com.wcabral.video.player.MediaPlayerApi
import com.wcabral.video.player.createMediaPlayer
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureVideosModule = module {
    viewModel { params ->
        val mediaPlayer = createMediaPlayer(MediaPlayerApi.ExoPlayer(androidContext()))
        VideosViewModel(
            gameId = params.get(),
            gamesRepository =  get(),
            mediaPlayer = mediaPlayer
        )
    }
}
