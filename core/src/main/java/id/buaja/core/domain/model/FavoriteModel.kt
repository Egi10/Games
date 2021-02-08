package id.buaja.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteModel(
    val id: Int? = null,
    val backgroundImage: String? = null,
    val genre: String? = null,
    val nameGame: String? = null,
    val description: String? = null
): Parcelable
