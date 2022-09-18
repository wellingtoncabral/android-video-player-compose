package com.wcabral.core.data.network.source

import com.wcabral.core.data.network.model.GetAllStoresResponse

interface StoresDataSource {
    suspend fun getAllStores(): GetAllStoresResponse
}
