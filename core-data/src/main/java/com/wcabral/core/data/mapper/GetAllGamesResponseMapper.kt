package com.wcabral.core.data.mapper

import com.wcabral.core.data.network.model.GetAllGamesResponse
import com.wcabral.core.data.network.model.Results
import com.wcabral.core.model.Game

fun Results.toEntity() = Game(
    id,
    name,
    backgroundImage,
)

fun GetAllGamesResponse.toEntity() : List<Game> = results.map { it.toEntity() }
