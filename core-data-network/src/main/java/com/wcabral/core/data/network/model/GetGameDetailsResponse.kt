package com.wcabral.core.data.network.model

import com.google.gson.annotations.SerializedName

data class GetGameDetailsResponse(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("name_original")
    val nameOriginal : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("background_image")
    val backgroundImage : String,
    val website : String,
    @SerializedName("rating")
    val rating : Double,
    @SerializedName("twitch_count")
    val twitchCount : Int,
    @SerializedName("youtube_count")
    val youtubeCount : Int,
)
