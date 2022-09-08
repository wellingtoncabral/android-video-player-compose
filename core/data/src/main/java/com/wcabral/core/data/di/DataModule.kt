package com.wcabral.core.data.di

import com.wcabral.core.data.repository.GamesRepository
import com.wcabral.core.data.repository.GamesRepositoryImpl
import org.koin.dsl.module

val dataModules = module {
    single<GamesRepository> { GamesRepositoryImpl(get()) }
}
