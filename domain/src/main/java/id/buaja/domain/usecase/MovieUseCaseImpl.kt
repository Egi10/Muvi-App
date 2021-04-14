package id.buaja.domain.usecase

import id.buaja.domain.model.Details
import id.buaja.domain.repository.MovieRepository

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

class MovieUseCaseImpl(private val repository: MovieRepository) : MovieUseCase {
    override suspend fun getBanner() = repository.getBanner()
    override suspend fun getPopular() = repository.getPopular()
    override suspend fun getComingSoon() = repository.getComingSoon()
    override suspend fun getAllPopular() = repository.getAllPopular()
    override suspend fun getDetails(idMovie: String) = repository.getDetails(idMovie)
    override suspend fun insertFavorite(details: Details) = repository.insertFavorite(details)
    override suspend fun deleteFavorite(idMovie: String) = repository.deleteFavorite(idMovie)
    override suspend fun getFavorite() = repository.getFavorite()
}