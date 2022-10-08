package com.wcabral.core.data.network.retrofit

object Endpoints {
    const val BASE_URL = "https://api.rawg.io/"
    const val GET_ALL_GAMES = "api/games"
    const val GET_ALL_MOVIES_BY_GAMES_ID = "api/games/{id}/movies"
    const val GET_ALL_STORES = "api/stores"
    const val GET_GAME_DETAIL = "api/games/{id}"
    const val GET_STORE_DETAIL = "api/stores/{id}"
}