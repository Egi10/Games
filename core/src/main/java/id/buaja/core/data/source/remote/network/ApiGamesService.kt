package id.buaja.core.data.source.remote.network

import id.buaja.core.data.source.remote.response.DevelopersResponse
import id.buaja.core.data.source.remote.response.GamesDetailResponse
import id.buaja.core.data.source.remote.response.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Julsapargi Nursam on 12/17/20.
 */

interface ApiGamesService {
    @GET("developers")
    suspend fun getDevelopers(): DevelopersResponse

    @GET("games")
    suspend fun getGames(): GamesResponse

    @GET("games/{id}")
    suspend fun getDetailGames(@Path("id") id: Int?): GamesDetailResponse
}