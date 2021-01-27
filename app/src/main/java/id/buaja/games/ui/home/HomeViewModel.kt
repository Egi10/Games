package id.buaja.games.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.core.data.Resource
import id.buaja.core.domain.model.DevelopersEntity
import id.buaja.core.domain.usescase.GamesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Julsapargi Nursam on 12/18/20.
 */
class HomeViewModel(private val useCase: GamesUseCase) : ViewModel() {
    private val _developersGame = MutableLiveData<List<DevelopersEntity>>()
    val developersGame: LiveData<List<DevelopersEntity>> get() = _developersGame

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun getDevelopers() {
        viewModelScope.launch(Dispatchers.Main) {
            useCase.getDevelopers().collect {
                when (it) {
                    is Resource.Loading -> {
                        _loading.value = true
                    }

                    is Resource.Success -> {
                        _loading.value = false
                        _developersGame.postValue(it.data)
                    }

                    is Resource.Error -> {
                        _error.postValue(it.message)
                    }
                }
            }
        }
    }
}