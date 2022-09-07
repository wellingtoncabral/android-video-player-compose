package com.wcabral.videoplayercompose

import android.app.Application
import com.wcabral.core.data.di.dataModules
import com.wcabral.core.data.network.di.networkModule
import com.wcabral.feature.videos.di.featureVideoModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

val appDependency = listOf(networkModule, dataModules, featureVideoModelModule)

class VideoPlayerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@VideoPlayerApplication)
            modules(
                appDependency
            )
        }
    }
}