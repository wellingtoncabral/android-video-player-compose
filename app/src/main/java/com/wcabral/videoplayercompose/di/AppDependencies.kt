package com.wcabral.videoplayercompose.di

import com.wcabral.core.data.di.dataModules
import com.wcabral.core.data.network.di.networkModule
import com.wcabral.feature.games.di.featureGamesModule
import com.wcabral.feature.videos.di.featureVideosModule
import com.wcabral.game.detail.di.featureGameDetailModule

val appDependencies = listOf(
    networkModule,
    dataModules,
    featureGamesModule,
    featureGameDetailModule,
    featureVideosModule,
)
