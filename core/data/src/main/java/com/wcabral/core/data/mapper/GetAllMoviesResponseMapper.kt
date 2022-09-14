package com.wcabral.core.data.mapper

import com.wcabral.core.data.network.model.GetAllMoviesResponse
import com.wcabral.core.data.network.model.MovieResultsResponse
import com.wcabral.core.model.Video

fun MovieResultsResponse.toModel() = Video(
    id = id,
    name = name,
    preview = preview,
    media480 = data.media480,
    mediaMax = data.mediaMax,
)

fun GetAllMoviesResponse.toModel() : List<Video> = results.map { it.toModel() }
