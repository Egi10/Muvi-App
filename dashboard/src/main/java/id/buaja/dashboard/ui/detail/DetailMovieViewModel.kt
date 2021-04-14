package id.buaja.dashboard.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.domain.ResultState
import id.buaja.domain.model.Details
import id.buaja.domain.usecase.MovieUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Julsapargi Nursam on 4/14/21.
 */

class DetailMovieViewModel(
    private val useCase: MovieUseCase,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    private val _success = MutableLiveData<Details>()
    val success: LiveData<Details> get() = _success

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getDetail(idMovie: String) {
        viewModelScope.launch(mainDispatcher) {
            useCase.getDetails(idMovie)
                .collect {
                    when(it) {
                        is ResultState.Success -> {
                            _success.postValue(it.data)
                        }

                        is ResultState.Error -> {
                            _error.postValue(it.exception.message)
                        }
                    }
                }
        }
    }
}