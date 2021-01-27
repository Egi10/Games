package id.buaja.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.buaja.core.data.source.local.entity.DevelopersGameEntity
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
}