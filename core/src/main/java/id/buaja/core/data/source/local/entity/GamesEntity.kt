package id.buaja.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Julsapargi Nursam on 1/28/21.
 */

@Entity(tableName = "games_table")
data class GamesEntity(
    @PrimaryKey
    @ColumnInfo(name = "_id")
    val id: Int? = null,

    @ColumnInfo(name = "background_image")
    val backgroundImage: String? = null,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "reviews_count")
    val reviewsCount: Int? = null
)