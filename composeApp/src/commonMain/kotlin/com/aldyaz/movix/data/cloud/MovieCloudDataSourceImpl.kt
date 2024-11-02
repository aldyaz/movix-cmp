package com.aldyaz.movix.data.cloud

import com.aldyaz.movix.core.network.HttpResult
import com.aldyaz.movix.data.extension.parseHttp
import com.aldyaz.movix.source.remote.MovixRemoteService
import com.aldyaz.movix.source.remote.model.MovieCreditsDto
import com.aldyaz.movix.source.remote.model.MovieDto
import com.aldyaz.movix.source.remote.model.MoviesDto

class MovieCloudDataSourceImpl(
    private val movixRemoteService: MovixRemoteService
) : MovieCloudDataSource {

    override suspend fun getNowPlayingMovies(): HttpResult<MoviesDto> {
        return parseHttp {
            movixRemoteService.getNowPlayingMovies()
        }
    }

    override suspend fun getPopularMovies(): HttpResult<MoviesDto> {
        return parseHttp {
            movixRemoteService.getPopularMovies()
        }
    }

    override suspend fun getTopRatedMovies(): HttpResult<MoviesDto> {
        return parseHttp {
            movixRemoteService.getTopRatedMovies()
        }
    }

    override suspend fun getMovieDetail(id: Long): HttpResult<MovieDto> {
        return parseHttp {
            movixRemoteService.getMovieDetail(id)
        }
    }

    override suspend fun getCredits(movieId: Long): HttpResult<MovieCreditsDto> {
        return parseHttp {
            movixRemoteService.getCredits(movieId)
        }
    }
}
