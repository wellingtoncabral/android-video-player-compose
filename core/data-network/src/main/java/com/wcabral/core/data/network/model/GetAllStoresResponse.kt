package com.wcabral.core.data.network.model

import com.google.gson.annotations.SerializedName

data class GetAllStoresResponse(
    @SerializedName("results")
    val results: List<StoresResultsResponse>
)

data class StoresResultsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("domain")
    val domain: String,
    @SerializedName("image_background")
    val background: String,
)
