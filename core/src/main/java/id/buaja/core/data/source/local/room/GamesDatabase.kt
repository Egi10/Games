package id.buaja.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.buaja.core.data.source.local.entity.DevelopersGameEntity
import id.buaja.core.data.source.local.entity.FavoriteEntity
import id.buaja.core.data.source.local.entity.GamesEntity

/**
 * Created by Julsapargi Nursam on 1/28/21.
 */

@Database(entities = [DevelopersGameEntity::class, GamesEntity::class, FavoriteEntity::class], version = 4, exportSchema = false)
abstract class GamesDatabase : RoomDatabase()  {
    abstract fun gamesDao(): GamesDao
}