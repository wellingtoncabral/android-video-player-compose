package com.wcabral.videoplayercompose.di

import com.wcabral.core.data.di.dataModules
import com.wcabral.core.data.network.di.networkModule
import com.wcabral.feature.game.detail.di.featureGameDetailModule
import com.wcabral.feature.games.di.featureGamesModule
import com.wcabral.feature.store.detail.di.featureStoreDetailModule
import com.wcabral.feature.videos.di.featureVideosModule

val appDependencies = listOf(
    networkModule,
    dataModules,
    featureGamesModule,
    featureGameDetailModule,
    featureVideosModule,
    featureStoreDetailModule,
)
