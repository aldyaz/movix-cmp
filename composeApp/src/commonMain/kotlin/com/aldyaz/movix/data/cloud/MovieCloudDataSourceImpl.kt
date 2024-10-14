package com.aldyaz.movix.data.cloud

import com.aldyaz.movix.core.network.HttpResult
import com.aldyaz.movix.data.extension.parseHttp
import com.aldyaz.movix.source.remote.TmdbRemoteService
import com.aldyaz.movix.source.remote.model.MovieDto
import com.aldyaz.movix.source.remote.model.MoviesDto
import com.aldyaz.movix.source.remote.model.TvShowsDto

class MovieCloudDataSourceImpl(
    private val tmdbRemoteService: TmdbRemoteService
) : MovieCloudDataSource {

    override suspend fun getNowPlayingMovies(): HttpResult<MoviesDto> {
        return parseHttp {
            tmdbRemoteService.getNowPlayingMovies()
        }
    }

    override suspend fun getPopularMovies(): HttpResult<MoviesDto> {
        return parseHttp {
            tmdbRemoteService.getPopularMovies()
        }
    }

    override suspend fun getTopRatedMovies(): HttpResult<MoviesDto> {
        return parseHttp {
            tmdbRemoteService.getTopRatedMovies()
        }
    }

    override suspend fun getMovieDetail(id: Long): HttpResult<MovieDto> {
        return parseHttp {
            tmdbRemoteService.getMovieDetail(id)
        }
    }

    override suspend fun getAiringTodayTvShows(): HttpResult<TvShowsDto> {
        return parseHttp {
            tmdbRemoteService.getAiringTodayTvShows()
        }
    }

    override suspend fun getOnTheAirTvShows(): HttpResult<TvShowsDto> {
        return parseHttp {
            tmdbRemoteService.getOnTheAirTvShows()
        }
    }
}
