package com.aldyaz.movix.data.cloud

import com.aldyaz.movix.core.network.HttpResult
import com.aldyaz.movix.source.remote.model.MovieDto
import com.aldyaz.movix.source.remote.model.MoviesDto
import com.aldyaz.movix.source.remote.model.TvShowsDto

interface MovieCloudDataSource {

    suspend fun getNowPlayingMovies(): HttpResult<MoviesDto>

    suspend fun getPopularMovies(): HttpResult<MoviesDto>

    suspend fun getTopRatedMovies(): HttpResult<MoviesDto>

    suspend fun getMovieDetail(id: Long): HttpResult<MovieDto>

    suspend fun getAiringTodayTvShows(): HttpResult<TvShowsDto>

    suspend fun getOnTheAirTvShows(): HttpResult<TvShowsDto>

}
