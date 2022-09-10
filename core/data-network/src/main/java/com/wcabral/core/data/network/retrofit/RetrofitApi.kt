package com.wcabral.core.data.network.retrofit

import com.wcabral.core.data.network.model.GetGameDetailsResponse
import com.wcabral.core.data.network.model.GetAllGamesResponse
import com.wcabral.core.data.network.model.GetAllStoresResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApi {
    @GET(Endpoints.GET_ALL_GAMES)
    suspend fun getAllGames(): GetAllGamesResponse

    @GET(Endpoints.GET_ALL_STORES)
    suspend fun getAllStores(): GetAllStoresResponse

    @GET(Endpoints.GET_GAME_DETAILS)
    suspend fun getGameDetails(
        @Path("id") gameId: Int
    ): GetGameDetailsResponse


//    @GET("api/games/{id}/movies")
//    suspend fun getGameVideos(
//        @Path("id") gameId: Int
//    ): GetGameVideosResponse
//
//    @GET("api/games?parent_platforms=1,2,3&search_precise=false&search_exact=false")
//    suspend fun searchGames(
//        @Query("search") query: String
//    ): SearchGamesResponse
}