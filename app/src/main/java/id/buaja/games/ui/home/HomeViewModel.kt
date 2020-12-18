package id.buaja.games.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.core.data.Resource
import id.buaja.core.domain.usescase.GamesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by Julsapargi Nursam on 12/18/20.
 */
class HomeViewModel(private val useCase: GamesUseCase): ViewModel() {

    fun getDevelopers() {
        viewModelScope.launch(Dispatchers.Main) {
            useCase.getDevelopers().collect {
                when(it) {
                    is Resource.Loading -> {
                        Timber.d("Loading")
                    }

                    is Resource.Success -> {
                        Timber.d(it.data.toString())
                    }

                    is Resource.Error -> {
                        Timber.d(it.message)
                    }
                }
            }
        }
    }
}