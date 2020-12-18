package id.buaja.core.data.source.remote.network

import id.buaja.core.data.source.remote.response.DevelopersResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Julsapargi Nursam on 12/17/20.
 */
interface ApiGamesService {
    @GET("developers")
    suspend fun getDevelopers(): DevelopersResponse
}