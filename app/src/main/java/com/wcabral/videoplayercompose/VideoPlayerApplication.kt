package com.wcabral.videoplayercompose

import android.app.Application
import com.wcabral.videoplayercompose.di.appDependencies
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class VideoPlayerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@VideoPlayerApplication)
            modules(
                appDependencies
            )
        }
    }
}