package id.buaja.favorite.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.core.domain.model.FavoriteModel
import id.buaja.core.domain.usescase.GamesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Julsapargi Nursam on 2/6/21.
 */

class FavoriteViewModel(private val useCase: GamesUseCase) : ViewModel() {
    private val _favorite = MutableLiveData<List<FavoriteModel>>()
    val favorite: LiveData<List<FavoriteModel>> get() = _favorite

    fun getFavorite() {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                useCase.getAllFavorite()
            }.collect {
                _favorite.postValue(it)
            }
        }
    }
}