package id.buaja.core.utils

import id.buaja.core.data.source.local.entity.DevelopersGameEntity
import id.buaja.core.data.source.remote.response.ResultsItem
import id.buaja.core.domain.model.DevelopersGameModel

/**
 * Created by Julsapargi Nursam on 12/18/20.
 */
object DataMapper {
    fun mapResponsesToEntities(list: List<ResultsItem>?) : List<DevelopersGameEntity> {
        val developersList = ArrayList<DevelopersGameEntity>()
        list?.map {
            val developersEntity = DevelopersGameEntity(
                imageBackground = it.imageBackground,
                id = it.id,
                name = it.name,
                slug = it.slug,
                sizeGame = it.games?.size
            )
            developersList.add(developersEntity)
        }

        return developersList
    }

    fun mapEntitiesToDomain(list: List<DevelopersGameEntity>?) : List<DevelopersGameModel> {
        val developersList = ArrayList<DevelopersGameModel>()
        list?.map {
            val developersEntity = DevelopersGameModel(
                imageBackground = it.imageBackground,
                id = it.id,
                name = it.name,
                slug = it.slug,
                sizeGame = it.sizeGame
            )
            developersList.add(developersEntity)
        }

        return developersList
    }
}