package id.buaja.domain.usecase

import id.buaja.domain.ResultState
import id.buaja.domain.model.Banner
import id.buaja.domain.model.ComingSoon
import id.buaja.domain.model.Popular
import kotlinx.coroutines.flow.Flow

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

interface MovieUseCase {
    suspend fun getBanner(): Flow<ResultState<List<Banner>>>
    suspend fun getPopular(): Flow<ResultState<List<Popular>>>
    suspend fun getComingSoon(): Flow<ResultState<List<ComingSoon>>>
}