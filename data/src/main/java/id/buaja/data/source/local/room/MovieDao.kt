package id.buaja.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.buaja.data.source.local.entity.MovieFavoriteEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Julsapargi Nursam on 4/14/21.
 */

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieFavoriteEntity)

    @Query("SELECT * FROM favorite WHERE id = :id")
    fun getFavoriteById(id: String): List<MovieFavoriteEntity>

    @Query("SELECT * FROM favorite")
    fun getFavorite(): Flow<List<MovieFavoriteEntity>>

    @Query("DELETE FROM favorite WHERE id = :id")
    fun deleteFavoriteById(id: String)
}