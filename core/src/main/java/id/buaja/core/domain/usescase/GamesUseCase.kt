package id.buaja.core.domain.usescase

import id.buaja.core.data.Resource
import id.buaja.core.domain.model.DevelopersEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Julsapargi Nursam on 12/18/20.
 */
interface GamesUseCase {
    fun getDevelopers(): Flow<Resource<List<DevelopersEntity>>>
}