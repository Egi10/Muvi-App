package id.buaja.domain.repository

import id.buaja.domain.ResultState
import id.buaja.domain.model.Banner
import id.buaja.domain.model.ComingSoon
import id.buaja.domain.model.Details
import id.buaja.domain.model.Popular
import kotlinx.coroutines.flow.Flow

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

interface MovieRepository {
    suspend fun getBanner(): Flow<ResultState<List<Banner>>>
    suspend fun getPopular(): Flow<ResultState<List<Popular>>>
    suspend fun getComingSoon(): Flow<ResultState<List<ComingSoon>>>
    suspend fun getAllPopular(): Flow<ResultState<List<Popular>>>
    suspend fun getDetails(idMovie: String): Flow<ResultState<Details>>
}