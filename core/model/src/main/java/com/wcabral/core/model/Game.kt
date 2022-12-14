package com.wcabral.core.model

data class Game(
    val id: Int,
    val name: String,
    val backgroundImage: String,
    val rating: Double = 0.0,
    val platforms: List<Platform> = emptyList(),
)

val previewGame = Game(
    id = 1,
    name = "Vampire: The Masquerade - Bloodlines 2",
    backgroundImage = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
    rating = 4.47,
    platforms = listOf(previewPlatformPC, previewPlatformLinux, previewPlatformXbox, previewPlatformNintendo)
)

val previewGames = listOf(
    previewGame,
    Game(
        id = 33,
        name = "The Witcher 3: Wild Hunt",
        backgroundImage = "https://media.rawg.io/media/games/618/618c2031a07bbff6b4f611f10b6bcdbc.jpg",
        rating = 3.50,
        platforms = listOf(previewPlatformPC, previewPlatformPlaystation)
    ),
    Game(
        id = 19,
        name = "Portal 2",
        backgroundImage = "https://media.rawg.io/media/games/328/3283617cb7d75d67257fc58339188742.jpg",
        rating = 2.87,
        platforms = listOf(previewPlatformUnknown)
    ),
    Game(
        id = 17,
        name = "Tomb Raider (2013)",
        backgroundImage = "https://media.rawg.io/media/games/021/021c4e21a1824d2526f925eff6324653.jpg",
        rating = 1.0,
        platforms = listOf(previewPlatformPC, previewPlatformPlaystation, previewPlatformApple, previewPlatformLinux)
    ),
)
