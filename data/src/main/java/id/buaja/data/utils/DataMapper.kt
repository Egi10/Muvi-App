package id.buaja.data.utils

import id.buaja.data.source.local.entity.MovieFavoriteEntity
import id.buaja.data.source.remote.response.MovieResponse
import id.buaja.domain.model.*

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

object DataMapper {
    fun mapResponseToDomainBanner(movieResponse: MovieResponse): List<Banner> {
        val listResult: ArrayList<Banner> = arrayListOf()

        movieResponse.results?.map {
            listResult.add(
                Banner(
                    backdropPath = it.backdropPath
                )
            )
        }

        return listResult
    }

    fun mapResponseToDomainPopular(movieResponse: MovieResponse): List<Popular> {
        val listResult: ArrayList<Popular> = arrayListOf()

        movieResponse.results?.map {
            listResult.add(
                Popular(
                    idMovie = it.id ?: 0,
                    backdropPath = it.posterPath
                )
            )
        }

        return listResult
    }

    fun mapResponseToDomainComingSoon(movieResponse: MovieResponse): List<ComingSoon> {
        val listResult: ArrayList<ComingSoon> = arrayListOf()

        movieResponse.results?.map {
            it.posterPath?.let { posterPath ->
                listResult.add(
                    ComingSoon(
                        idMovie = it.id.toString(),
                        backdropPath = posterPath
                    )
                )
            }
        }

        return listResult
    }

    fun mapResponseToEntity(details: Details): MovieFavoriteEntity {
        return MovieFavoriteEntity(
            id = details.idMovie,
            image = details.image,
            title = details.title,
            date = details.duration,
            genre = details.genre
        )
    }

    fun mapEntityToDomain(list: List<MovieFavoriteEntity>): List<MovieFavorite> {
        return list.map {
            MovieFavorite(
                id = it.id,
                image = it.image,
                title = it.title,
                date = it.date,
                genre = it.genre
            )
        }
    }
}