package com.wcabral.core.data.repository

import com.wcabral.core.model.Game
import com.wcabral.core.model.Movie
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    suspend fun getAllGames(): Flow<List<Game>>
    suspend fun getAllMovies(gameId: Int): Flow<List<Movie>>
//    suspend fun getGameDetails(gameId: Int): GameDetailsEntity
}