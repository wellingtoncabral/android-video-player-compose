package com.wcabral.core.data.repository

import com.wcabral.core.data.mapper.toModel
import com.wcabral.core.data.network.GamesDataSource
import com.wcabral.core.model.Game
import com.wcabral.core.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GamesRepositoryImpl(private val networkDataSource: GamesDataSource) : GamesRepository {

    override suspend fun getAllGames(): Flow<List<Game>> = flow {
        emit(networkDataSource.getAllGames().toModel())
        // TODO - handle the exception case
    }

    override suspend fun getAllMovies(gameId: Int): Flow<List<Video>> = flow {
        emit(networkDataSource.getAllMovies(gameId).toModel())
    }

}