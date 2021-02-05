package id.buaja.core.data

import id.buaja.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkOnlyBoundResource<ResultType, RequestType> {
    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())

        val apiResponse = this@NetworkOnlyBoundResource.createCall()
        apiResponse.collect {
            when (it) {
                is ApiResponse.Success -> {
                    val data = loadData(it.data)
                    emit(Resource.Success(data))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(it))
                }
                is ApiResponse.Error -> {
                    // Sending Error
                    emit(
                        Resource.Error<ResultType>(it.errorMessage)
                    )
                }
            }
        }
    } as Flow<Resource<ResultType>>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract fun loadData(data: RequestType): ResultType?

    fun asFlow(): Flow<Resource<ResultType>> = result

}