package com.wcabral.core.data.mapper

import com.wcabral.core.data.network.model.GetAllMoviesByGameIdResponse
import com.wcabral.core.data.network.model.MovieResultsResponse
import com.wcabral.core.model.Movie

fun MovieResultsResponse.toModel() = Movie(
    id = id,
    name = name,
    preview = preview,
    media480 = data.media480,
    mediaMax = data.mediaMax,
)

fun GetAllMoviesByGameIdResponse.toModel() : List<Movie> = results.map { it.toModel() }
