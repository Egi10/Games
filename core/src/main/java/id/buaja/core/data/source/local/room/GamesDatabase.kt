package id.buaja.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.buaja.core.data.source.local.entity.DevelopersGameEntity

/**
 * Created by Julsapargi Nursam on 1/28/21.
 */

@Database(entities = [DevelopersGameEntity::class], version = 1, exportSchema = false)
abstract class GamesDatabase : RoomDatabase()  {
    abstract fun gamesDao(): GamesDao
}