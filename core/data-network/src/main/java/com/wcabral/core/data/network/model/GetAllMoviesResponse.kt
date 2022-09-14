package com.wcabral.core.data.network.model

import com.google.gson.annotations.SerializedName

data class GetAllMoviesResponse(
    @SerializedName("results")
    val results: List<MovieResultsResponse>,
)

data class MovieResultsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("preview")
    val preview: String,
    @SerializedName("data")
    val data: MovieData,
)

data class MovieData(
    @SerializedName("480")
    val media480: String,
    @SerializedName("max")
    val mediaMax: String,
)
