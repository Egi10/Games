package id.buaja.core.utils

import id.buaja.core.data.source.local.entity.DevelopersGameEntity
import id.buaja.core.data.source.local.entity.FavoriteEntity
import id.buaja.core.data.source.local.entity.GamesEntity
import id.buaja.core.data.source.remote.response.GamesDetailResponse
import id.buaja.core.data.source.remote.response.ResultsItem
import id.buaja.core.data.source.remote.response.ResultsItems
import id.buaja.core.domain.model.DevelopersGameModel
import id.buaja.core.domain.model.FavoriteModel
import id.buaja.core.domain.model.GamesDetailModel
import id.buaja.core.domain.model.GamesModel

/**
 * Created by Julsapargi Nursam on 12/18/20.
 */
object DataMapper {
    fun mapResponsesToEntities(list: List<ResultsItem>?): List<DevelopersGameEntity> {
        val developersList = ArrayList<DevelopersGameEntity>()
        list?.map {
            val developersEntity = DevelopersGameEntity(
                imageBackground = it.imageBackground,
                id = it.id,
                name = it.name,
                slug = it.slug,
                sizeGame = it.gamesCount
            )
            developersList.add(developersEntity)
        }

        return developersList
    }

    fun mapEntitiesToDomain(list: List<DevelopersGameEntity>?): List<DevelopersGameModel> {
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

    fun mapResponseToEntityGames(list: List<ResultsItems>?): List<GamesEntity> {
        val newList = ArrayList<GamesEntity>()
        list?.map {
            val gamesEntity = GamesEntity(
                id = it.id,
                backgroundImage = it.backgroundImage,
                title = it.name,
                reviewsCount = it.reviewsCount
            )
            newList.add(gamesEntity)
        }
        return newList
    }

    fun mapEntityToDomainGames(list: List<GamesEntity>?): List<GamesModel> {
        val newList = ArrayList<GamesModel>()
        list?.map {
            val gamesModel = GamesModel(
                id = it.id,
                backgroundImage = it.backgroundImage,
                title = it.title,
                reviewCount = it.reviewsCount
            )
            newList.add(gamesModel)
        }
        return newList
    }

    fun mapResponseToEntityDetailGames(detailResponse: GamesDetailResponse): GamesDetailModel {
        val listGenre = ArrayList<String>()
        detailResponse.genres?.map {
            listGenre.add(it?.name.toString())
        }
        return GamesDetailModel(
            id = detailResponse.id,
            backgroundImage = detailResponse.backgroundImage,
            genre = listGenre.joinToString(separator = ", "),
            nameGame = detailResponse.name,
            description = detailResponse.description
        )
    }

    fun mapDomainToEntityFavorite(favoriteModel: GamesDetailModel) = FavoriteEntity(
        id = favoriteModel.id,
        backgroundImage = favoriteModel.backgroundImage,
        nameGame = favoriteModel.nameGame,
        genre = favoriteModel.genre,
        description = favoriteModel.description
    )

    fun mapEntityToDomainFavorite(favoriteModel: List<FavoriteEntity>): List<FavoriteModel> {
        val newList = ArrayList<FavoriteModel>()
        favoriteModel.map {
            val favorite = FavoriteModel(
                id = it.id,
                backgroundImage = it.backgroundImage,
                nameGame = it.nameGame,
                genre = it.genre,
                description = it.description
            )
            newList.add(favorite)
        }
        return newList
    }
}