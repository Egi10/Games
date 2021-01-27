package id.buaja.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Julsapargi Nursam on 1/28/21.
 */

@Entity(tableName = "developers_games")
data class DevelopersGameEntity (
    @PrimaryKey
    @ColumnInfo(name = "_id")
    val id: Int? = null,

    @ColumnInfo(name = "image_background")
    val imageBackground: String? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "slug")
    val slug: String? = null,

    @ColumnInfo(name = "size_game")
    val sizeGame: Int? = null,
)