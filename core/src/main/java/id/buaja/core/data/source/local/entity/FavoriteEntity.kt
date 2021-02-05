package id.buaja.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "_id")
    val id: Int? = null,

    @ColumnInfo(name = "background_image")
    val backgroundImage: String? = null,

    @ColumnInfo(name = "genre")
    val genre: String? = null,

    @ColumnInfo(name = "name_game")
    val nameGame: String? = null,

    @ColumnInfo(name = "description")
    val description: String? = null
)
