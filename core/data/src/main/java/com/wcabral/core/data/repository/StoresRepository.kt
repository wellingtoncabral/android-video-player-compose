package com.wcabral.core.data.repository

import com.wcabral.core.model.Store
import kotlinx.coroutines.flow.Flow

interface StoresRepository {
    suspend fun getAllStores(): Flow<List<Store>>
    suspend fun getStoreDetail(storeId: Int): Flow<Store>
}
