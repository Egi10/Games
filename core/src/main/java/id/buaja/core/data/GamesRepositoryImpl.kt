package id.buaja.core.data

import id.buaja.core.data.source.local.LocalDataSource
import id.buaja.core.data.source.remote.RemoteDataSource
import id.buaja.core.data.source.remote.network.ApiResponse
import id.buaja.core.data.source.remote.response.DevelopersResponse
import id.buaja.core.domain.model.DevelopersGameModel
import id.buaja.core.domain.repository.GamesRepository
import id.buaja.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * Created by Julsapargi Nursam on 12/18/20.
 */
class GamesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : GamesRepository {
    override fun getDevelopers(): Flow<Resource<List<DevelopersGameModel>>> =
        object : NetworkBoundResource<List<DevelopersGameModel>, DevelopersResponse>() {
            override fun loadFromDB(): Flow<List<DevelopersGameModel>> {
                return localDataSource.getAllDevelopersGame().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            // Mengatur Load data terus menurus
            // atau mengambil data yang sudah
            // ada di DB Local (true = ambil network | false = local)
            override fun shouldFetch(data: List<DevelopersGameModel>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<DevelopersResponse>> {
                return remoteDataSource.getDevelopers()
            }

            override suspend fun saveCallResult(data: DevelopersResponse) {
                val result = DataMapper.mapResponsesToEntities(data.results)
                localDataSource.insertDevelopersGame(result)
            }
        }.asFlow()
}