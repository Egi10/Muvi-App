package id.buaja.data.source.remote.network

import id.buaja.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovie(
        @QueryMap map: Map<String, String>
    ): MovieResponse
}