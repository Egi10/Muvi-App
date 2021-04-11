package id.buaja.data.utils

import id.buaja.data.source.remote.network.ApiResult
import id.buaja.domain.LoaderState
import id.buaja.domain.ResultState
import kotlinx.coroutines.flow.*

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

abstract class NetworkBoundResource<ResultType, RequestType> {
    private var resultState: Flow<ResultState<ResultType>> = flow {
        createCall()
            .onStart {
                LoaderState.ShowLoading
            }
            .onCompletion {
                LoaderState.HideLoading
            }
            .collect { result ->
                when(result) {
                    is ApiResult.Success -> {
                        val resultResponse = mapperData(result.data)
                        emit(ResultState.Success(resultResponse))
                    }

                    is ApiResult.Error -> {
                        emit(ResultState.Error(result.exception))
                    }
                }
            }
    }

    abstract suspend fun createCall(): Flow<ApiResult<RequestType>>
    abstract fun mapperData(data: RequestType): ResultType

    fun asFlow(): Flow<ResultState<ResultType>> = resultState
}