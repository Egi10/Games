package id.buaja.core.domain.usescase

import id.buaja.core.data.Resource
import id.buaja.core.domain.model.DevelopersGameModel
import id.buaja.core.domain.repository.GamesRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Julsapargi Nursam on 12/18/20.
 */
class GameUseCaseImpl(private val repository: GamesRepository): GamesUseCase {
    override fun getDevelopers(): Flow<Resource<List<DevelopersGameModel>>> {
        return repository.getDevelopers()
    }
}