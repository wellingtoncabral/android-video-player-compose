package com.wcabral.core.data.repository

import com.wcabral.core.data.mapper.toModel
import com.wcabral.core.data.network.source.StoresDataSource
import com.wcabral.core.model.Store
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StoresRepositoryImpl(private val networkDataSource: StoresDataSource) : StoresRepository {

    override suspend fun getAllStores(): Flow<List<Store>> = flow {
        emit(networkDataSource.getAllStores().toModel())
    }

}