package com.wcabral.core.data.network.model

import com.google.gson.annotations.SerializedName

data class GetAllStoresResponse(
    @SerializedName("results")
    val results: List<StoreResponse>
)
