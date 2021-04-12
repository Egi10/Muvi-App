package id.buaja.data.source.remote.network

import id.buaja.data.source.remote.response.CreditsResponse
import id.buaja.data.source.remote.response.DetailsResponse
import id.buaja.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/{id_movie}/credits")
    suspend fun getCredits(
        @Path("id_movie") idMovie: String,
        @QueryMap map: Map<String, String>
    ): CreditsResponse

    @GET("movie/{id_movie}")
    suspend fun getDetails(
        @Path("id_movie") idMovie: String,
        @QueryMap map: Map<String, String>
    ): DetailsResponse
}