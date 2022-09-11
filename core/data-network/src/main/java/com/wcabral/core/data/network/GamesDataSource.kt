package com.wcabral.core.data.network

import com.wcabral.core.data.network.model.GetAllGamesResponse
import com.wcabral.core.data.network.model.GetAllMoviesByGameIdResponse

interface GamesDataSource {
    suspend fun getAllGames(): GetAllGamesResponse
    suspend fun getAllMovies(gameId: Int): GetAllMoviesByGameIdResponse
//    suspend fun getGameDetails(gameId: Int): GameDetailsEntityz
}
