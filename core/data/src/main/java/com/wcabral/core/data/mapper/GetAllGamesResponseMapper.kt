package com.wcabral.core.data.mapper

import com.wcabral.core.data.network.model.GetAllGamesResponse
import com.wcabral.core.data.network.model.PlatformResponse
import com.wcabral.core.data.network.model.ResultsResponse
import com.wcabral.core.model.Game
import com.wcabral.core.model.Platform
import com.wcabral.core.model.PlatformType

fun ResultsResponse.toModel() = Game(
    id = id,
    name = name,
    backgroundImage = backgroundImage,
    rating = rating,
    platforms= platforms?.toModel() ?: emptyList()
)

fun GetAllGamesResponse.toModel() : List<Game> = results.map { it.toModel() }
