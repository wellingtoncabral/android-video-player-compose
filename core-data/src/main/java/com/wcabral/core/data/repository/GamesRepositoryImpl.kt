package com.wcabral.core.data.repository

import com.wcabral.core.data.mapper.toEntity
import com.wcabral.core.data.network.GamesNetworkDataSource
import com.wcabral.core.model.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GamesRepositoryImpl(private val networkDataSource: GamesNetworkDataSource) : GamesRepository {

    override suspend fun getAllGames(): Flow<List<Game>> = flow {
        emit(networkDataSource.getAllGames().toEntity())
        // TODO - handle the exception case
    }

}