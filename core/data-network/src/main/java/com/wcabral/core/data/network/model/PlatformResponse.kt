package com.wcabral.core.data.network.model

import com.google.gson.annotations.SerializedName

data class PlatformResponse(
    @SerializedName("platform")
    val platform: PlatformContent,
)

data class PlatformContent(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
)
