package com.wcabral.core.data.network.source

import com.wcabral.core.data.network.model.GetAllStoresResponse
import com.wcabral.core.data.network.model.StoreResponse

interface StoresDataSource {
    suspend fun getAllStores(): GetAllStoresResponse
    suspend fun getStoreDetail(storeId: Int): StoreResponse
}
