package id.buaja.data.utils

import id.buaja.data.source.remote.response.MovieResponse
import id.buaja.domain.model.Banner
import id.buaja.domain.model.ComingSoon
import id.buaja.domain.model.Popular

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

object DataMapper {
    fun mapResponseToEntityBanner(movieResponse: MovieResponse): List<Banner> {
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

    fun mapResponseToEntityPopular(movieResponse: MovieResponse): List<Popular> {
        val listResult: ArrayList<Popular> = arrayListOf()

        movieResponse.results?.map {
            listResult.add(
                Popular(
                    backdropPath = it.backdropPath
                )
            )
        }

        return listResult
    }

    fun mapResponseToEntityComingSoon(movieResponse: MovieResponse): List<ComingSoon> {
        val listResult: ArrayList<ComingSoon> = arrayListOf()

        movieResponse.results?.map {
            it.backdropPath?.let { backdropPath ->
                listResult.add(
                    ComingSoon(
                        backdropPath = backdropPath
                    )
                )
            }
        }

        return listResult
    }
}