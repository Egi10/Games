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
import kotlinx.coroutines.withContext
import timber.log.Timber

class DetailGamesViewModel(private val useCase: GamesUseCase) : ViewModel() {
    private val _success = MutableLiveData<GamesDetailModel>()
    val success: LiveData<GamesDetailModel> get() = _success

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    private val _successDelete = MutableLiveData<String>()
    val successDelete: LiveData<String> get() = _successDelete

    fun getGamesDetail(id: Int?) {
        viewModelScope.launch(Dispatchers.Main) {
            useCase.getGamesDetail(id)
                .collect {
                    when (it) {
                        is Resource.Success -> {
                            _success.postValue(it.data)
                        }

                        is Resource.Error -> {
                            _error.postValue(it.message)
                        }

                        is Resource.Loading -> {
                            Timber.tag("Disini").d(it.loading.toString())
                            _loading.value = it.loading
                        }
                    }
                }
        }
    }

    fun checkFavorite(id: Int?) {
        viewModelScope.launch(Dispatchers.Main) {
            useCase.getFavoriteById(id)
                .collect {
                    _isFavorite.value = !it.isNullOrEmpty()
                }
        }
    }

    fun insertFavorite(gamesDetailModel: GamesDetailModel) {
        useCase.insertFavorite(gamesDetailModel)
    }

    fun deleteFavoriteId(id: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                useCase.deleteFavoriteId(id)
            }
            _successDelete.postValue("Data Deleted Successfully")
        }
    }
}