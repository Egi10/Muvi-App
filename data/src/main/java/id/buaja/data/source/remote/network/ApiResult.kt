package id.buaja.data.source.remote.network

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

sealed class ApiResult<out R> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val exception: Exception) : ApiResult<Nothing>()
}
