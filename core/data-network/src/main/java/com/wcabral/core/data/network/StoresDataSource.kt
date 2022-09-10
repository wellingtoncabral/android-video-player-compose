package com.wcabral.core.data.network

import com.wcabral.core.data.network.model.GetAllStoresResponse

interface StoresDataSource {
    suspend fun getAllStores(): GetAllStoresResponse
}
