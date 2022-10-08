package com.wcabral.core.data.mapper

import com.wcabral.core.data.network.model.StoreResponse
import com.wcabral.core.model.Store

fun StoreResponse.toModel() = Store(
    id = id,
    name = name,
    domain = domain,
    backgroundImage = background,
    description = description,
)
