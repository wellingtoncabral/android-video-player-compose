package com.wcabral.core.data.network.retrofit

import com.wcabral.core.data.network.GamesDataSource
import com.wcabral.core.data.network.model.GetAllGamesResponse
import com.wcabral.core.data.network.model.GetAllMoviesResponse

class GamesDataSourceImpl(
    private val retrofitApi: RetrofitApi
) : GamesDataSource {

    override suspend fun getAllGames(): GetAllGamesResponse {
        return retrofitApi.getAllGames()
    }

    override suspend fun getAllMovies(gameId: Int): GetAllMoviesResponse {
        return retrofitApi.getAllMovies(gameId)
    }

}