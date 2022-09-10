package com.wcabral.core.data.mapper

import com.wcabral.core.data.network.model.GetAllStoresResponse
import com.wcabral.core.data.network.model.StoresResultsResponse
import com.wcabral.core.model.Store

fun StoresResultsResponse.toModel() = Store(
    id = id,
    name = name,
    domain = domain,
    imageBackground = background,
)

fun GetAllStoresResponse.toModel() : List<Store> = results.map { it.toModel() }
