package id.buaja.data.source.remote

import id.buaja.data.BuildConfig
import id.buaja.data.source.remote.network.ApiResult
import id.buaja.data.source.remote.network.ApiService
import id.buaja.data.source.remote.response.CreditsResponse
import id.buaja.data.source.remote.response.MovieResponse
import id.buaja.data.utils.Constant
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

class MovieRemoteDataSource(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getBanner(): Flow<ApiResult<MovieResponse>> {
        return flow {
            try {
                val request = mapOf(
                    "api_key" to BuildConfig.MOVIE_API_KEY,
                    "language" to Constant.language,
                    "sort_by" to Constant.sortBy,
                    "include_adult" to Constant.includeAdult,
                    "include_video" to Constant.includeVideo,
                    "page" to Constant.page
                )
                val response = apiService.getMovie(request)
                emit(ApiResult.Success(response))
            } catch (e: Exception) {
                emit(ApiResult.Error(e))
            }
        }.flowOn(ioDispatcher)
    }

    suspend fun getPopular(): Flow<ApiResult<MovieResponse>> {
        return flow {
            try {
                val request = mapOf(
                    "api_key" to BuildConfig.MOVIE_API_KEY,
                    "language" to Constant.language,
                    "sort_by" to Constant.sortBy,
                    "include_adult" to Constant.includeAdult,
                    "include_video" to Constant.includeVideo,
                    "page" to Constant.page
                )
                val response = apiService.getMovie(request)
                emit(ApiResult.Success(response))
            } catch (e: Exception) {
                emit(ApiResult.Error(e))
            }
        }.flowOn(ioDispatcher)
    }

    suspend fun getComingSoon(): Flow<ApiResult<MovieResponse>> {
        return flow {
            try {
                val yearNow = Calendar.getInstance().get(Calendar.YEAR)
                val request = mapOf(
                    "api_key" to BuildConfig.MOVIE_API_KEY,
                    "language" to Constant.language,
                    "sort_by" to Constant.sortBy,
                    "include_adult" to Constant.includeAdult,
                    "include_video" to Constant.includeVideo,
                    "page" to Constant.page,
                    "year" to "${yearNow + 1}"
                )
                val response = apiService.getMovie(request)
                emit(ApiResult.Success(response))
            } catch (e: Exception) {
                emit(ApiResult.Error(e))
            }
        }.flowOn(ioDispatcher)
    }

    suspend fun getCredits(idMovie: String): Flow<ApiResult<CreditsResponse>> {
        return flow {
            try {
                val request = mapOf(
                    "api_key" to BuildConfig.MOVIE_API_KEY,
                    "language" to Constant.language
                )
                val response = apiService.getCredits(idMovie, request)
                emit(ApiResult.Success(response))
            } catch (e: Exception) {
                emit(ApiResult.Error(e))
            }
        }.flowOn(ioDispatcher)
    }
}