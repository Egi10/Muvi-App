package id.buaja.dashboard.ui.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.domain.ResultState
import id.buaja.domain.model.Popular
import id.buaja.domain.usecase.MovieUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PopularViewModel(
    private val useCase: MovieUseCase,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _success = MutableLiveData<List<Popular>>()
    val success: LiveData<List<Popular>> get() = _success

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getAllFavorite() {
        viewModelScope.launch(mainDispatcher) {
            useCase.getAllPopular()
                .onStart {
                    _loading.value = true
                }
                .onCompletion {
                    _loading.value = false
                }
                .collect {
                    when (it) {
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