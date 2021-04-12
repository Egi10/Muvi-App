package id.buaja.dashboard.ui.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.domain.ResultState
import id.buaja.domain.usecase.MovieUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PopularViewModel(
    private val useCase: MovieUseCase,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    fun getAllFavorite() {
        Log.d("Disini", "Disini")
        viewModelScope.launch(mainDispatcher) {
            try {
                useCase.getAllPopular()
                    .collect {
                        when(it) {
                            is ResultState.Success -> {
                                it.data.map {
                                    Log.d("Disini", "Disini : $it")
                                }
                            }

                            is ResultState.Error -> {
                                Log.d("Disini", "Disini ${it.exception.message.toString()}")
                            }
                        }
                    }
            } catch (e: Exception) {
                Log.d("Disini", "Disini $e")
            }
        }
    }
}