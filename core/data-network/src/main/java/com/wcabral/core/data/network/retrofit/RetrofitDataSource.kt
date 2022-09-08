package com.wcabral.core.data.network.retrofit

import com.wcabral.core.data.network.GamesNetworkDataSource
import com.wcabral.core.data.network.model.GetAllGamesResponse

class RetrofitDataSource(
    private val retrofitApi: RetrofitApi
) : GamesNetworkDataSource {

    override suspend fun getAllGames(): GetAllGamesResponse {
        return retrofitApi.getAllGames()
    }

}