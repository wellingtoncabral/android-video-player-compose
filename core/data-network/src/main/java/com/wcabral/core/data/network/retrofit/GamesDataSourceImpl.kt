package com.wcabral.core.data.network.retrofit

import com.wcabral.core.data.network.GamesDataSource
import com.wcabral.core.data.network.model.GetAllGamesResponse

class GamesDataSourceImpl(
    private val retrofitApi: RetrofitApi
) : GamesDataSource {

    override suspend fun getAllGames(): GetAllGamesResponse {
        return retrofitApi.getAllGames()
    }

}