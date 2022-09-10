package com.wcabral.core.model

data class Store(
    val id: Int,
    val name: String,
    val domain: String,
    val imageBackground: String,
)

val previewStore = Store(
    id = 1,
    name = "Steam",
    domain = "store.steampowered.com",
    imageBackground = "https://media.rawg.io/media/games/8cc/8cce7c0e99dcc43d66c8efd42f9d03e3.jpg"
)

val previewStores = listOf(
    previewStore,
    Store(
        id = 2,
        name = "PlayStation Store",
        domain = "store.playstation.com",
        imageBackground = "https://media.rawg.io/media/games/4be/4be6a6ad0364751a96229c56bf69be59.jpg"
    )
)
