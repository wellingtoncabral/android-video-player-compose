package com.wcabral.feature.games.di

import com.wcabral.feature.games.GamesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureGamesModule = module {
    viewModel {
        GamesViewModel(
            gamesRepository =  get(),
            storesRepository = get(),
        )
    }
}
