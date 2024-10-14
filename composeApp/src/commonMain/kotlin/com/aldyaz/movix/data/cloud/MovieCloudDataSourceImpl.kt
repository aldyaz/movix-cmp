package com.aldyaz.movix.data.cloud

import com.aldyaz.movix.core.exception.HttpException
import com.aldyaz.movix.core.network.HttpResult
import com.aldyaz.movix.source.remote.TmdbRemoteService
import com.aldyaz.movix.source.remote.model.MovieDto
import com.aldyaz.movix.source.remote.model.MoviesDto
import com.aldyaz.movix.source.remote.model.TvShowsDto

class MovieCloudDataSourceImpl(
    private val tmdbRemoteService: TmdbRemoteService
) : MovieCloudDataSource {

    override suspend fun getNowPlayingMovies(): HttpResult<MoviesDto> {
        return try {
            val result = tmdbRemoteService.getNowPlayingMovies()
            HttpResult.Success(result)
        } catch (err: HttpException) {
            HttpResult.Error(err)
        }
    }

    override suspend fun getPopularMovies(): HttpResult<MoviesDto> {
        return try {
            val result = tmdbRemoteService.getPopularMovies()
            HttpResult.Success(result)
        } catch (err: HttpException) {
            HttpResult.Error(err)
        }
    }

    override suspend fun getTopRatedMovies(): HttpResult<MoviesDto> {
        return try {
            val result = tmdbRemoteService.getTopRatedMovies()
            HttpResult.Success(result)
        } catch (err: HttpException) {
            HttpResult.Error(err)
        }
    }

    override suspend fun getMovieDetail(id: Long): HttpResult<MovieDto> {
        return try {
            val result = tmdbRemoteService.getMovieDetail(id)
            HttpResult.Success(result)
        } catch (err: HttpException) {
            HttpResult.Error(err)
        }
    }

    override suspend fun getAiringTodayTvShows(): HttpResult<TvShowsDto> {
        return try {
            val result = tmdbRemoteService.getAiringTodayTvShows()
            HttpResult.Success(result)
        } catch (err: HttpException) {
            HttpResult.Error(err)
        }
    }

    override suspend fun getOnTheAirTvShows(): HttpResult<TvShowsDto> {
        return try {
            val result = tmdbRemoteService.getOnTheAirTvShows()
            HttpResult.Success(result)
        } catch (err: HttpException) {
            HttpResult.Error(err)
        }
    }
}
