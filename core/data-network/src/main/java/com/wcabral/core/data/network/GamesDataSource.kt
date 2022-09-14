package com.wcabral.core.data.network

import com.wcabral.core.data.network.model.GetAllGamesResponse
import com.wcabral.core.data.network.model.GetAllMoviesResponse

interface GamesDataSource {
    suspend fun getAllGames(): GetAllGamesResponse
    suspend fun getAllMovies(gameId: Int): GetAllMoviesResponse
//    suspend fun getGameDetails(gameId: Int): GameDetailsEntity
}
