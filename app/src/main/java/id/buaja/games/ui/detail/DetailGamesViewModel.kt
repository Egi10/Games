package id.buaja.games.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.core.data.Resource
import id.buaja.core.domain.model.GamesDetailModel
import id.buaja.core.domain.usescase.GamesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailGamesViewModel(private val useCase: GamesUseCase) : ViewModel() {
    private val _success = MutableLiveData<GamesDetailModel>()
    val success: LiveData<GamesDetailModel> get() = _success

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun getGamesDetail(id: Int?) {
        viewModelScope.launch(Dispatchers.Main) {
            useCase.getGamesDetail(id).collect {
                when (it) {
                    is Resource.Success -> {
                        _loading.value = false
                        _success.postValue(it.data)
                    }

                    is Resource.Error -> {
                        _loading.value = false
                        _error.postValue(it.message)
                    }

                    is Resource.Loading -> {
                        _loading.value = true
                    }
                }
            }
        }
    }

    fun insertFavorite(gamesDetailModel: GamesDetailModel) {
        useCase.insertFavorite(gamesDetailModel)
    }
}