package com.wcabral.core.data.network

import com.wcabral.core.data.network.model.GetAllGamesResponse

interface GamesDataSource {
    suspend fun getAllGames(): GetAllGamesResponse
//    suspend fun getGameDetails(gameId: Int): GameDetailsEntityz
}
