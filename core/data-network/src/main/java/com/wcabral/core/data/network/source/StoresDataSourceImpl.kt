package com.wcabral.core.data.network.source

import com.wcabral.core.data.network.model.GetAllStoresResponse
import com.wcabral.core.data.network.model.StoreResponse
import com.wcabral.core.data.network.retrofit.RetrofitApi

class StoresDataSourceImpl(
    private val retrofitApi: RetrofitApi
) : StoresDataSource {

    override suspend fun getAllStores(): GetAllStoresResponse {
        return retrofitApi.getAllStores()
    }

    override suspend fun getStoreDetail(storeId: Int): StoreResponse {
        return retrofitApi.getStoreDetail(storeId)
    }
}
