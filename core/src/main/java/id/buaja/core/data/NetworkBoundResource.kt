package id.buaja.core.data

import id.buaja.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

/**
 * Created by Julsapargi Nursam on 12/18/20.
 */

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading(data = null, loading = null))
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }
                is ApiResponse.Error -> {
                    // Sending Error
                    emit(
                        Resource.Error<ResultType>(apiResponse.errorMessage)
                    )

                    // Show All Database Room
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }
            }
        } else {
            emitAll(loadFromDB().map {
                Resource.Success(it)
            })
        }
    } as Flow<Resource<ResultType>>

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}