package id.buaja.data.di

import androidx.room.Room
import id.buaja.data.BuildConfig
import id.buaja.data.repository.MovieRepositoryImpl
import id.buaja.data.source.local.LocalMovieDataSource
import id.buaja.data.source.local.room.MovieDatabase
import id.buaja.data.source.remote.MovieRemoteDataSource
import id.buaja.data.source.remote.network.ApiService
import id.buaja.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

val dataModule = module {
    factory {
        Dispatchers.IO
    }

    single {
        OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }

    single {
        MovieRemoteDataSource(get(), get())
    }

    single {
        LocalMovieDataSource(get())
    }

    single<MovieRepository> {
        MovieRepositoryImpl(get(), get())
    }

    factory {
        get<MovieDatabase>().movieDao()
    }

    single {
        Room.databaseBuilder(
            get(), MovieDatabase::class.java, "Movie.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}