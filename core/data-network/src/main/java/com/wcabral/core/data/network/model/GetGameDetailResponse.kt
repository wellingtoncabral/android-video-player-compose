package com.wcabral.core.data.network.model

import com.google.gson.annotations.SerializedName

data class GetGameDetailResponse(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("description_raw")
    val description : String,
    @SerializedName("background_image")
    val backgroundImage : String,
    @SerializedName("website")
    val website : String,
    @SerializedName("rating")
    val rating : Double,
    @SerializedName("movies_count")
    val moviesCount: Int,
    @SerializedName("platforms")
    val platforms: List<PlatformResponse>?,
)
