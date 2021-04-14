package id.buaja.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.buaja.data.source.local.entity.MovieFavoriteEntity

@Database(entities = [MovieFavoriteEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}