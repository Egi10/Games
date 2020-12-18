package id.buaja.core.data

import id.buaja.core.data.source.remote.RemoteDataSource
import id.buaja.core.data.source.remote.network.ApiResponse
import id.buaja.core.data.source.remote.response.DevelopersResponse
import id.buaja.core.domain.model.DevelopersEntity
import id.buaja.core.domain.repository.GamesRepository
import id.buaja.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Julsapargi Nursam on 12/18/20.
 */
class GamesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : GamesRepository {
    private val list: MutableList<DevelopersEntity> = mutableListOf()
    override fun getDevelopers(): Flow<Resource<List<DevelopersEntity>>> =
        object : NetworkBoundResource<List<DevelopersEntity>, DevelopersResponse>() {
            override fun loadFromDB(): Flow<List<DevelopersEntity>> {
                return flow {
//                    val list = listOf(
//                        DevelopersEntity(
//                            imageBackground = "it.imageBackground",
//                            id = 1,
//                            name = "it.name",
//                            slug = "it.slug"
//                        )
//                    )
                    emit(list)
                }
            }

            // Mengatur Load data terus menurus
            // atau mengambil data yang sudah
            // ada di DB Local (true = ambil network | false = local)
            override fun shouldFetch(data: List<DevelopersEntity>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<DevelopersResponse>> {
                return remoteDataSource.getDevelopers()
            }

            override suspend fun saveCallResult(data: DevelopersResponse) {
                data.results?.let { DataMapper.mapResponsesToEntities(it) }
                list.clear()
                data.results?.map {
                    val developersEntity = DevelopersEntity(
                        imageBackground = it.imageBackground,
                        id = it.id,
                        name = it.name,
                        slug = it.slug
                    )
                    list.add(developersEntity)
                }
            }

        }.asFlow()
}