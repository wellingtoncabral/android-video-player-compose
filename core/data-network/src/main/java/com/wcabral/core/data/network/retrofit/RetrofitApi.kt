package com.wcabral.core.data.network.retrofit

import com.wcabral.core.data.network.model.GetGameDetailResponse
import com.wcabral.core.data.network.model.GetAllGamesResponse
import com.wcabral.core.data.network.model.GetAllMoviesResponse
import com.wcabral.core.data.network.model.GetAllStoresResponse
import com.wcabral.core.data.network.model.StoreResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApi {
    @GET(Endpoints.GET_ALL_GAMES)
    suspend fun getAllGames(): GetAllGamesResponse

    @GET(Endpoints.GET_ALL_MOVIES_BY_GAMES_ID)
    suspend fun getAllMovies(@Path("id") gameId: Int): GetAllMoviesResponse

    @GET(Endpoints.GET_ALL_STORES)
    suspend fun getAllStores(): GetAllStoresResponse

    @GET(Endpoints.GET_GAME_DETAIL)
    suspend fun getGameDetail(
        @Path("id") gameId: Int
    ): GetGameDetailResponse

    @GET(Endpoints.GET_STORE_DETAIL)
    suspend fun getStoreDetail(
        @Path("id") storeId: Int
    ): StoreResponse


//    @GET("api/games?parent_platforms=1,2,3&search_precise=false&search_exact=false")
//    suspend fun searchGames(
//        @Query("search") query: String
//    ): SearchGamesResponse
}