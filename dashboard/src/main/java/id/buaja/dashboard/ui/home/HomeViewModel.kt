package id.buaja.dashboard.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.domain.ResultState
import id.buaja.domain.model.Banner
import id.buaja.domain.model.ComingSoon
import id.buaja.domain.model.Popular
import id.buaja.domain.usecase.MovieUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: MovieUseCase,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _banner = MutableLiveData<List<Banner>>()
    val banner: LiveData<List<Banner>> get() = _banner

    private val _popular = MutableLiveData<List<Popular>>()
    val popular: LiveData<List<Popular>> get() = _popular

    private val _comingSoon = MutableLiveData<List<ComingSoon>>()
    val comingSoon: LiveData<List<ComingSoon>> get() = _comingSoon

    fun getBanner() {
        viewModelScope.launch(mainDispatcher) {
            useCase.getBanner()
                .onStart {
                    _loading.value = true
                }
                .onCompletion {
                    _loading.value = false
                }
                .zip(useCase.getPopular()) { banner, popular ->
                    when (banner) {
                        is ResultState.Success -> {
                            _banner.postValue(banner.data)
                        }

                        is ResultState.Error -> {
                            Log.d("Error", banner.exception.message.toString())
                        }
                    }

                    when (popular) {
                        is ResultState.Success -> {
                            _popular.postValue(popular.data)
                        }

                        is ResultState.Error -> {
                            Log.d("Error", popular.exception.message.toString())
                        }
                    }
                }
                .zip(useCase.getComingSoon()) { _, comingSoon ->
                    when (comingSoon) {
                        is ResultState.Success -> {
                            _comingSoon.postValue(comingSoon.data)
                        }

                        is ResultState.Error -> {
                            Log.d("Error", comingSoon.exception.message.toString())
                        }
                    }
                }
                .collect()
        }
    }
}