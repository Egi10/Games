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

    private val _empty = MutableLiveData<String>()
    val empty: LiveData<String> get() = _empty

    fun getFavorite() {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                useCase.getAllFavorite()
            }.collect {
                if (it.isEmpty()) {
                    _favorite.postValue(it)
                    _empty.value = "Data Favorite Masih Kosong"
                } else {
                    _favorite.postValue(it)
                }
            }
        }
    }
}