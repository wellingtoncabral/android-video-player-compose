package com.wcabral.core.data.mapper

import com.wcabral.core.data.network.model.GetAllStoresResponse
import com.wcabral.core.model.Store

fun GetAllStoresResponse.toModel() : List<Store> = results.map { it.toModel() }
