package id.buaja.core.domain.usescase

import id.buaja.core.data.Resource
import id.buaja.core.domain.model.DevelopersGameModel
import id.buaja.core.domain.model.FavoriteModel
import id.buaja.core.domain.model.GamesDetailModel
import id.buaja.core.domain.model.GamesModel
import id.buaja.core.domain.repository.GamesRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Julsapargi Nursam on 12/18/20.
 */
class GameUseCaseImpl(private val repository: GamesRepository) : GamesUseCase {
    override fun getDevelopers(): Flow<Resource<List<DevelopersGameModel>>> =
        repository.getDevelopers()

    override fun getGames(): Flow<Resource<List<GamesModel>>> = repository.getGames()
    override fun getGamesDetail(id: Int?): Flow<Resource<GamesDetailModel>> =
        repository.getGamesDetail(id)

    override fun insertFavorite(gamesDetailModel: GamesDetailModel) =
        repository.insertFavorite(gamesDetailModel)

    override fun getAllFavorite(): Flow<List<FavoriteModel>> = repository.getAllFavorite()
    override fun deleteFavoriteId(id: Int?) = repository.deleteFavoriteId(id)
    override fun getFavoriteById(id: Int?) = repository.getFavoriteById(id)
}