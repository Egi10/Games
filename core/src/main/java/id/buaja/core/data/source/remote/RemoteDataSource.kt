package id.buaja.core.data.source.remote

import id.buaja.core.data.source.remote.network.ApiGamesService
import id.buaja.core.data.source.remote.network.ApiResponse
import id.buaja.core.data.source.remote.response.DevelopersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

/**
 * Created by Julsapargi Nursam on 12/18/20.
 */
class RemoteDataSource(private val apiGamesService: ApiGamesService) {
    suspend fun getDevelopers(): Flow<ApiResponse<DevelopersResponse>> {
        return flow {
            try {
                val response = apiGamesService.getDevelopers()
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}