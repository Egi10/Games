package id.buaja.core.utils

import id.buaja.core.data.source.remote.response.DevelopersResponse
import id.buaja.core.data.source.remote.response.ResultsItem
import id.buaja.core.domain.model.DevelopersEntity

/**
 * Created by Julsapargi Nursam on 12/18/20.
 */
object DataMapper {
    fun mapResponsesToEntities(list: List<ResultsItem>) : List<DevelopersEntity> {
        val developersList = ArrayList<DevelopersEntity>()
        list.map {
            val developersEntity = DevelopersEntity(
                imageBackground = it.imageBackground,
                id = it.id,
                name = it.name,
                slug = it.slug
            )
            developersList.add(developersEntity)
        }

        return developersList
    }
}