package com.wcabral.feature.videos.di

import com.wcabral.feature.videos.VideosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureVideoModelModule = module {
    viewModel {
        VideosViewModel(get())
    }
}
