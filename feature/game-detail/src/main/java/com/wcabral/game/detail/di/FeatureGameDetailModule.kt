package com.wcabral.game.detail.di

import com.wcabral.game.detail.GameDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureGameDetailModule = module {
    viewModel { params ->
        GameDetailViewModel(
            gameId = params.get(),
            gamesRepository =  get(),
        )
    }
}
