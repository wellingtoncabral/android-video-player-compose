package com.wcabral.core.data.network.source

import com.wcabral.core.data.network.model.GetAllGamesResponse
import com.wcabral.core.data.network.model.GetAllMoviesResponse
import com.wcabral.core.data.network.model.GetGameDetailResponse

interface GamesDataSource {
    suspend fun getAllGames(): GetAllGamesResponse
    suspend fun getAllMovies(gameId: Int): GetAllMoviesResponse
    suspend fun getGameDetail(gameId: Int): GetGameDetailResponse
}
