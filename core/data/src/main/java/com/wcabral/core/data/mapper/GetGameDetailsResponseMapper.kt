package com.wcabral.core.data.mapper

import com.wcabral.core.data.network.model.GetGameDetailResponse
import com.wcabral.core.model.GameDetail

fun GetGameDetailResponse.toModel() = GameDetail(
    id = id,
    name = name,
    description = description,
    backgroundImage = backgroundImage,
    rating = rating,
    moviesCount = moviesCount,
    platforms= platforms?.toModel() ?: emptyList(),
)
