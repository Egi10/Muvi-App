package id.buaja.data.source.local

import id.buaja.data.source.local.entity.MovieFavoriteEntity
import id.buaja.data.source.local.room.MovieDao
import kotlinx.coroutines.*

/**
 * Created by Julsapargi Nursam on 4/14/21.
 */

class LocalMovieDataSource(
    private val dao: MovieDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun insert(movieFavoriteEntity: MovieFavoriteEntity) {
        CoroutineScope(ioDispatcher).launch {
            dao.insert(movieFavoriteEntity)
        }
    }

    suspend fun getFavoriteById(idMovie: String): List<MovieFavoriteEntity> = withContext(ioDispatcher) {
        dao.getFavoriteById(idMovie)
    }

    suspend fun deleteFavoriteById(idMovie: String) = withContext(ioDispatcher) {
        dao.deleteFavoriteById(idMovie)
    }

    suspend fun getFavorite() = withContext(ioDispatcher) {
        dao.getFavorite()
    }
}