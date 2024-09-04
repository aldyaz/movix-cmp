package com.aldyaz.movix.data.repository

import com.aldyaz.movix.core.domain.ResultState
import com.aldyaz.movix.core.network.HttpResult
import com.aldyaz.movix.data.cloud.MovieCloudDataSource
import com.aldyaz.movix.domain.mapper.HttpExceptionToDomainMapper
import com.aldyaz.movix.domain.mapper.MovieListToDomainMapper
import com.aldyaz.movix.domain.mapper.MovieToDomainMapper
import com.aldyaz.movix.domain.model.MovieDomainModel
import com.aldyaz.movix.domain.model.MovieListDomainModel
import com.aldyaz.movix.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieCloudDataSource: MovieCloudDataSource,
    private val movieListToDomainMapper: MovieListToDomainMapper,
    private val movieToDomainMapper: MovieToDomainMapper,
    private val httpExceptionToDomainMapper: HttpExceptionToDomainMapper
) : MovieRepository {

    override suspend fun getNowPlayingMovies(): ResultState<MovieListDomainModel> {
        return when (val result = movieCloudDataSource.getNowPlayingMovies()) {
            is HttpResult.Success -> ResultState.Success(
                movieListToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }

    override suspend fun getPopularMovies(): ResultState<MovieListDomainModel> {
        return when (val result = movieCloudDataSource.getPopularMovies()) {
            is HttpResult.Success -> ResultState.Success(
                movieListToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }

    override suspend fun getTopRatedMovies(): ResultState<MovieListDomainModel> {
        return when (val result = movieCloudDataSource.getTopRatedMovies()) {
            is HttpResult.Success -> ResultState.Success(
                movieListToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }

    override suspend fun getMovieDetail(id: Long): ResultState<MovieDomainModel> {
        return when (val result = movieCloudDataSource.getMovieDetail(id)) {
            is HttpResult.Success -> ResultState.Success(
                movieToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }
}