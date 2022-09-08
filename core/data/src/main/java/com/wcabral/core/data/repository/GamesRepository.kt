package com.wcabral.core.data.repository

import com.wcabral.core.model.Game
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    suspend fun getAllGames(): Flow<List<Game>>
//    suspend fun getGameDetails(gameId: Int): GameDetailsEntity
}