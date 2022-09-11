package com.wcabral.core.model

data class Movie(
    val id: Int,
    val name: String,
    val preview: String,
    val media480: String,
    val mediaMax: String,
)

val previewMovie = Movie(
    id = 1,
    name = "GTA Online: Smuggler's Run Trailer",
    preview = "https://media.rawg.io/media/movies/d8a/d8a61a3a12e52114afdbc28f2c813f5c.jpg",
    media480 = "https://steamcdn-a.akamaihd.net/steam/apps/256693661/movie480.mp4",
    mediaMax = "https://steamcdn-a.akamaihd.net/steam/apps/256693661/movie_max.mp4",
)

val previewMovies = listOf(
    previewMovie,
    Movie(
        id = 2,
        name = "GTA Online: Tiny Racers Trailer",
        preview = "https://media.rawg.io/media/movies/d8a/d8a61a3a12e52114afdbc28f2c813f5c.jpg",
        media480 = "https://steamcdn-a.akamaihd.net/steam/apps/256693661/movie480.mp4",
        mediaMax = "https://steamcdn-a.akamaihd.net/steam/apps/256693661/movie_max.mp4",
    ),
    Movie(
        id = 3,
        name = "GTA Online: Gunrunning Trailer",
        preview = "https://media.rawg.io/media/movies/d8a/d8a61a3a12e52114afdbc28f2c813f5c.jpg",
        media480 = "https://steamcdn-a.akamaihd.net/steam/apps/256693661/movie480.mp4",
        mediaMax = "https://steamcdn-a.akamaihd.net/steam/apps/256693661/movie_max.mp4",
    ),
)
