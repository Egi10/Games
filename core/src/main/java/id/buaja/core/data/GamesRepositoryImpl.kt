package id.buaja.core.data

import id.buaja.core.data.source.local.LocalDataSource
import id.buaja.core.data.source.remote.RemoteDataSource
import id.buaja.core.data.source.remote.network.ApiResponse
import id.buaja.core.data.source.remote.response.DevelopersResponse
import id.buaja.core.data.source.remote.response.GamesDetailResponse
import id.buaja.core.data.source.remote.response.GamesResponse
import id.buaja.core.domain.model.DevelopersGameModel
import id.buaja.core.domain.model.FavoriteModel
import id.buaja.core.domain.model.GamesDetailModel
import id.buaja.core.domain.model.GamesModel
import id.buaja.core.domain.repository.GamesRepository
import id.buaja.core.utils.AppExecutors
import id.buaja.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

/**
 * Created by Julsapargi Nursam on 12/18/20.
 */
class GamesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
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

            override suspend fun createCall(): Flow<ApiResponse<DevelopersResponse>> =
                remoteDataSource.getDevelopers()

            override suspend fun saveCallResult(data: DevelopersResponse) {
                val result = DataMapper.mapResponsesToEntities(data.results)
                localDataSource.insertDevelopersGame(result)
            }
        }.asFlow()

    override fun getGames(): Flow<Resource<List<GamesModel>>> =
        object : NetworkBoundResource<List<GamesModel>, GamesResponse>() {
            override fun loadFromDB(): Flow<List<GamesModel>> = localDataSource.getAllGames().map {
                DataMapper.mapEntityToDomainGames(it)
            }

            override fun shouldFetch(data: List<GamesModel>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<GamesResponse>> =
                remoteDataSource.getGames()

            override suspend fun saveCallResult(data: GamesResponse) {
                val result = DataMapper.mapResponseToEntityGames(data.results)
                localDataSource.insertGames(result)
            }

        }.asFlow()

    override fun getGamesDetail(id: Int?): Flow<Resource<GamesDetailModel>> =
        object : NetworkOnlyBoundResource<GamesDetailModel, GamesDetailResponse>() {
            override suspend fun createCall(): Flow<ApiResponse<GamesDetailResponse>> =
                remoteDataSource.getDetailGames(id)

            override fun loadData(data: GamesDetailResponse): GamesDetailModel =
                DataMapper.mapResponseToEntityDetailGames(data)

        }.asFlow()

    override fun insertFavorite(favoriteModel: GamesDetailModel) {
        val favoriteEntity = DataMapper.mapDomainToEntityFavorite(favoriteModel)
        appExecutors.diskIO().execute {
            localDataSource.insertFavorite(favoriteEntity)
        }
    }

    override fun getAllFavorite(): Flow<List<FavoriteModel>> =
        localDataSource.getAllFavorite().map {
            DataMapper.mapEntityToDomainFavorite(it)
        }

    override fun deleteFavoriteId(id: Int?) {
        appExecutors.diskIO().execute {
            localDataSource.deleteFavoriteId(id)
        }
    }
}