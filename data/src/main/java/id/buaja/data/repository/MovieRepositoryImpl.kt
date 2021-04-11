package id.buaja.data.repository

import id.buaja.data.source.remote.MovieRemoteDataSource
import id.buaja.data.source.remote.response.MovieResponse
import id.buaja.data.utils.DataMapper
import id.buaja.data.utils.NetworkBoundResource
import id.buaja.domain.ResultState
import id.buaja.domain.model.Banner
import id.buaja.domain.model.ComingSoon
import id.buaja.domain.model.Popular
import id.buaja.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

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

}