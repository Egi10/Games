package id.buaja.core.domain.repository

import id.buaja.core.data.Resource
import id.buaja.core.domain.model.DevelopersGameModel
import id.buaja.core.domain.model.GamesDetailModel
import id.buaja.core.domain.model.GamesModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Julsapargi Nursam on 12/18/20.
 */
interface GamesRepository {
    fun getDevelopers(): Flow<Resource<List<DevelopersGameModel>>>
    fun getGames(): Flow<Resource<List<GamesModel>>>
    fun getGamesDetail(id: Int?): Flow<Resource<GamesDetailModel>>
}