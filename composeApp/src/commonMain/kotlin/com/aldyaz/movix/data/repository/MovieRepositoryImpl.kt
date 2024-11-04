package com.aldyaz.movix.data.repository

import com.aldyaz.movix.core.domain.ResultState
import com.aldyaz.movix.core.domain.exception.UnknownException
import com.aldyaz.movix.core.network.HttpResult
import com.aldyaz.movix.data.cloud.MovieCloudDataSource
import com.aldyaz.movix.data.local.MovieLocalDataSource
import com.aldyaz.movix.domain.mapper.HttpExceptionToDomainMapper
import com.aldyaz.movix.domain.mapper.MovieCastsToDomainMapper
import com.aldyaz.movix.domain.mapper.MovieDbToDomainMapper
import com.aldyaz.movix.domain.mapper.MovieDomainToDbMapper
import com.aldyaz.movix.domain.mapper.MovieToDomainMapper
import com.aldyaz.movix.domain.mapper.MoviesDtoToDomainMapper
import com.aldyaz.movix.domain.model.MovieCastDomainModel
import com.aldyaz.movix.domain.model.MovieDomainModel
import com.aldyaz.movix.domain.model.MovieListDomainModel
import com.aldyaz.movix.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val movieCloudDataSource: MovieCloudDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val moviesDtoToDomainMapper: MoviesDtoToDomainMapper,
    private val movieToDomainMapper: MovieToDomainMapper,
    private val movieCastToDomainMapper: MovieCastsToDomainMapper,
    private val movieDbToDomainMapper: MovieDbToDomainMapper,
    private val movieDomainToDbMapper: MovieDomainToDbMapper,
    private val httpExceptionToDomainMapper: HttpExceptionToDomainMapper
) : MovieRepository {

    override suspend fun getNowPlayingMovies(): ResultState<MovieListDomainModel> {
        return when (val result = movieCloudDataSource.getNowPlayingMovies()) {
            is HttpResult.Success -> ResultState.Success(
                moviesDtoToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }

    override suspend fun getPopularMovies(): ResultState<MovieListDomainModel> {
        return when (val result = movieCloudDataSource.getPopularMovies()) {
            is HttpResult.Success -> ResultState.Success(
                moviesDtoToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }

    override suspend fun getTopRatedMovies(): ResultState<MovieListDomainModel> {
        return when (val result = movieCloudDataSource.getTopRatedMovies()) {
            is HttpResult.Success -> ResultState.Success(
                moviesDtoToDomainMapper(result.data)
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

    override suspend fun getCredits(movieId: Long): ResultState<List<MovieCastDomainModel>> {
        return when (val result = movieCloudDataSource.getCredits(movieId)) {
            is HttpResult.Success -> ResultState.Success(
                movieCastToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }

    override fun getFavoriteMovies(): Flow<List<MovieDomainModel>> {
        return movieLocalDataSource.getMovies().map {
            List(it.size) { index ->
                movieDbToDomainMapper(it[index])
            }
        }
    }

    override fun checkFavorite(movieId: Long): Flow<Boolean> {
        return movieLocalDataSource.checkFavorite(movieId)
    }

    override fun saveFavorite(movie: MovieDomainModel): ResultState<Unit> {
        return try {
            ResultState.Success(
                movieLocalDataSource.saveFavorite(
                    movieDomainToDbMapper(movie)
                )
            )
        } catch (err: Throwable) {
            ResultState.Error(exception = UnknownException())
        }
    }
}