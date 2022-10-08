package com.wcabral.feature.store.detail.di

import com.wcabral.feature.store.detail.StoreDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureStoreDetailModule = module {
    viewModel { params ->
        StoreDetailViewModel(
            storeId = params.get(),
            storeRepository = get(),
        )
    }
}
