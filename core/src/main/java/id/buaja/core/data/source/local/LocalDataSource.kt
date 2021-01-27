package id.buaja.core.data.source.local

import id.buaja.core.data.source.local.entity.DevelopersGameEntity
import id.buaja.core.data.source.local.room.GamesDao
import kotlinx.coroutines.flow.Flow

/**
 * Created by Julsapargi Nursam on 1/28/21.
 */

class LocalDataSource(private val gamesDao: GamesDao) {
    suspend fun insertDevelopersGame(developersGame: List<DevelopersGameEntity>) =
        gamesDao.insertDevelopersGame(developersGame)

    fun getAllDevelopersGame(): Flow<List<DevelopersGameEntity>> = gamesDao.getAllDevelopersGame()
}