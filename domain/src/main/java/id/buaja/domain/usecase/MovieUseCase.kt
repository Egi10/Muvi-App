package id.buaja.domain.usecase

import id.buaja.domain.ResultState
import id.buaja.domain.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

interface MovieUseCase {
    suspend fun getBanner(): Flow<ResultState<List<Banner>>>
    suspend fun getPopular(): Flow<ResultState<List<Popular>>>
    suspend fun getComingSoon(): Flow<ResultState<List<ComingSoon>>>
    suspend fun getAllPopular(): Flow<ResultState<List<Popular>>>
    suspend fun getDetails(idMovie: String): Flow<ResultState<Details>>
    suspend fun insertFavorite(details: Details)
    suspend fun deleteFavorite(idMovie: String)
    suspend fun getFavorite(): Flow<List<MovieFavorite>>
}