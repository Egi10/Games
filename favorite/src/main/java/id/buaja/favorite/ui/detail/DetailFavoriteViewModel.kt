package id.buaja.favorite.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.core.domain.usescase.GamesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailFavoriteViewModel(private val useCase: GamesUseCase) : ViewModel() {
    private val _successDelete = MutableLiveData<String>()
    val successDelete: LiveData<String> get() = _successDelete

    fun deleteFavoriteId(id: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                useCase.deleteFavoriteId(id)
            }
            _successDelete.postValue("Data Deleted Successfully")
        }
    }
}