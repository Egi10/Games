package id.buaja.core.data.source.local.room

import androidx.room.*
import id.buaja.core.data.source.local.entity.DevelopersGameEntity
import id.buaja.core.data.source.local.entity.FavoriteEntity
import id.buaja.core.data.source.local.entity.GamesEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Julsapargi Nursam on 1/28/21.
 */

@Dao
interface GamesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevelopersGame(developersGame: List<DevelopersGameEntity>)

    @Query("Select * from developers_games ORDER BY _id DESC")
    fun getAllDevelopersGame(): Flow<List<DevelopersGameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GamesEntity>)

    @Query("Select * from games_table")
    fun getAllGames(): Flow<List<GamesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: FavoriteEntity)

    @Query("SELECT * FROM favorite")
    fun getAllFavorite(): Flow<List<FavoriteEntity>>

    @Query("DELETE FROM favorite WHERE _id = :id")
    fun deleteFavoriteId(id: Int?)
}