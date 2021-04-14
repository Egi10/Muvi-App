package id.buaja.dashboard.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.domain.model.MovieFavorite
import id.buaja.domain.usecase.MovieUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val useCase: MovieUseCase,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _success = MutableLiveData<List<MovieFavorite>>()
    val success: LiveData<List<MovieFavorite>> get() = _success

    init {
        getFavorite()
    }

    private fun getFavorite() {
        viewModelScope.launch(mainDispatcher) {
            useCase.getFavorite().collect {
                _success.postValue(it)
            }
        }
    }
}