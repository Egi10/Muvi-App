package id.buaja.domain

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

sealed class ResultState<out R> {
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val exception: Exception) : ResultState<Nothing>()
}

sealed class LoaderState {
    object ShowLoading: LoaderState()
    object HideLoading: LoaderState()
}
