package com.wcabral.core.model

data class GameDetail(
    val id: Int,
    val name: String,
    val description: String,
    val backgroundImage: String,
    val rating: Double,
    val moviesCount: Int,
    val platforms: List<Platform>,
)

val previewGameDetail = GameDetail(
    id = 1,
    name = "Grand Theft Auto V",
    description = "Rockstar Games went bigger, since their previous installme",
    backgroundImage = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
    rating = 4.5,
    moviesCount = 3,
    platforms = previewPlatforms,
)
