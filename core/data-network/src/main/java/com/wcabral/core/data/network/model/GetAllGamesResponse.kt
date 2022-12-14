package com.wcabral.core.data.network.model

import com.google.gson.annotations.SerializedName

data class GetAllGamesResponse(
    @SerializedName("results")
    val results: List<ResultsResponse>,
)

data class ResultsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("background_image")
    val backgroundImage: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("platforms")
    val platforms: List<PlatformResponse>?,
)
