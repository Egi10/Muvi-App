package id.buaja.data.repository

import id.buaja.data.source.remote.MovieRemoteDataSource
import id.buaja.data.source.remote.network.ApiResult
import id.buaja.data.source.remote.response.MovieResponse
import id.buaja.data.utils.DataMapper
import id.buaja.data.utils.NetworkBoundResource
import id.buaja.domain.ResultState
import id.buaja.domain.model.*
import id.buaja.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

class MovieRepositoryImpl(private val remoteDataSource: MovieRemoteDataSource) : MovieRepository {
    override suspend fun getBanner(): Flow<ResultState<List<Banner>>> =
        object : NetworkBoundResource<List<Banner>, MovieResponse>() {
            override suspend fun createCall() =
                remoteDataSource.getBanner()

            override fun mapperData(data: MovieResponse) =
                DataMapper.mapResponseToEntityBanner(data)

        }.asFlow()

    override suspend fun getPopular(): Flow<ResultState<List<Popular>>> =
        object : NetworkBoundResource<List<Popular>, MovieResponse>() {
            override suspend fun createCall() =
                remoteDataSource.getPopular()

            override fun mapperData(data: MovieResponse) =
                DataMapper.mapResponseToEntityPopular(data)

        }.asFlow()

    override suspend fun getComingSoon(): Flow<ResultState<List<ComingSoon>>> =
        object : NetworkBoundResource<List<ComingSoon>, MovieResponse>() {
            override suspend fun createCall() =
                remoteDataSource.getComingSoon()

            override fun mapperData(data: MovieResponse) =
                DataMapper.mapResponseToEntityComingSoon(data)

        }.asFlow()

    @ExperimentalCoroutinesApi
    @FlowPreview
    override suspend fun getAllPopular(): Flow<ResultState<List<Popular>>> {
        return channelFlow {
            remoteDataSource.getPopular()
                .flatMapMerge { resultMovie ->
                    val resultState: MutableList<Popular> = mutableListOf()

                    when (resultMovie) {
                        is ApiResult.Success -> {
                            resultMovie.data.results?.map { result ->
                                remoteDataSource.getCredits(result.id.toString())
                                    .collect { resultCast ->
                                        when (resultCast) {
                                            is ApiResult.Success -> {
                                                val actor: MutableList<String> = mutableListOf()

                                                resultCast.data.cast?.map { listCash ->
                                                    actor.add(listCash.name.toString())
                                                }

                                                resultState.add(
                                                    Popular(
                                                        idMovie = result.id ?: 0,
                                                        backdropPath = result.posterPath,
                                                        title = result.title,
                                                        actor = actor.joinToString(separator = ", ")
                                                    )
                                                )
                                            }

                                            is ApiResult.Error -> {
                                                send(ResultState.Error(resultCast.exception))
                                            }
                                        }
                                    }
                            }
                        }

                        is ApiResult.Error -> {
                            send(ResultState.Error(resultMovie.exception))
                        }
                    }
                    return@flatMapMerge flow {
                        emit(resultState)
                    }
                }
                .flatMapMerge {
                    val resultState: MutableList<Popular> = mutableListOf()

                    it.map { popular ->
                        remoteDataSource.getDetails(popular.idMovie.toString())
                            .collect { detail ->
                                when (detail) {
                                    is ApiResult.Success -> {
                                        val genreName: MutableList<String> = mutableListOf()
                                        detail.data.genres?.map { genre ->
                                            genreName.add(genre.name.toString())
                                        }

                                        resultState.add(
                                            Popular(
                                                idMovie = popular.idMovie,
                                                backdropPath = popular.backdropPath,
                                                title = popular.title,
                                                actor = popular.actor,
                                                genre = genreName.joinToString(separator = ", ")
                                            )
                                        )
                                    }

                                    is ApiResult.Error -> {
                                        send(ResultState.Error(detail.exception))
                                    }
                                }
                            }
                    }
                    return@flatMapMerge flow {
                        emit(resultState)
                    }
                }
                .collect {
                    send(ResultState.Success(it))
                }
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override suspend fun getDetails(idMovie: String): Flow<ResultState<Details>> {
        return channelFlow {
            remoteDataSource.getDetails(idMovie)
                .flatMapMerge { resultDetail ->
                    var details = Details()

                    when(resultDetail) {
                        is ApiResult.Success -> {
                            val listCast: MutableList<Cast> = mutableListOf()
                            remoteDataSource.getCredits(idMovie)
                                .collect { credits ->
                                    when(credits) {
                                        is ApiResult.Success -> {
                                            credits.data.cast?.map {
                                                listCast.add(
                                                    Cast(
                                                        name = it.name.toString(),
                                                        image = it.profilePath.toString()
                                                    )
                                                )
                                            }

                                            val listNameGenre: MutableList<String> = mutableListOf()

                                            resultDetail.data.genres?.map {
                                                listNameGenre.add(it.name.toString())
                                            }
                                            details = Details(
                                                poster = resultDetail.data.posterPath.toString(),
                                                title = resultDetail.data.title.toString(),
                                                duration = resultDetail.data.releaseDate.toString(),
                                                genre = listNameGenre.joinToString(separator = " . "),
                                                overview = resultDetail.data.overview.toString(),
                                                cast = listCast
                                            )
                                        }

                                        is ApiResult.Error -> {
                                            send(ResultState.Error(credits.exception))
                                        }
                                    }
                                }
                        }

                        is ApiResult.Error -> {
                            send(ResultState.Error(resultDetail.exception))
                        }
                    }

                    return@flatMapMerge flow {
                        emit(details)
                    }
                }
                .collect {
                    send(ResultState.Success(it))
                }
        }
    }
}