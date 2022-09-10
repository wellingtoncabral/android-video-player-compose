package com.wcabral.core.data.network.retrofit

import com.wcabral.core.data.network.StoresDataSource
import com.wcabral.core.data.network.model.GetAllStoresResponse

class StoresDataSourceImpl(
    private val retrofitApi: RetrofitApi
) : StoresDataSource {

    override suspend fun getAllStores(): GetAllStoresResponse {
        return retrofitApi.getAllStores()
    }

}