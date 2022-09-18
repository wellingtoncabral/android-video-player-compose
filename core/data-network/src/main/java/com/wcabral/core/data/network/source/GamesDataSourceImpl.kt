package com.wcabral.core.data.network.source

import com.wcabral.core.data.network.model.GetAllGamesResponse
import com.wcabral.core.data.network.model.GetAllMoviesResponse
import com.wcabral.core.data.network.model.GetGameDetailResponse
import com.wcabral.core.data.network.retrofit.RetrofitApi

class GamesDataSourceImpl(
    private val retrofitApi: RetrofitApi
) : GamesDataSource {

    override suspend fun getAllGames(): GetAllGamesResponse {
        return retrofitApi.getAllGames()
    }

    override suspend fun getAllMovies(gameId: Int): GetAllMoviesResponse {
        return retrofitApi.getAllMovies(gameId)
    }

    override suspend fun getGameDetail(gameId: Int): GetGameDetailResponse {
        return retrofitApi.getGameDetail(gameId)
    }
}
