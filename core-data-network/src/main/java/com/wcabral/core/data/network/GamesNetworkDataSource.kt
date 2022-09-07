package com.wcabral.core.data.network

import com.wcabral.core.data.network.model.GetAllGamesResponse

interface GamesNetworkDataSource {
    suspend fun getAllGames(): GetAllGamesResponse
//    suspend fun getGameDetails(gameId: Int): GameDetailsEntity
}
