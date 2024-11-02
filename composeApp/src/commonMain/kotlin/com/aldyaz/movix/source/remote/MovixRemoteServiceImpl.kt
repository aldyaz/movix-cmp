package com.aldyaz.movix.source.remote

import com.aldyaz.movix.core.network.apiCall
import com.aldyaz.movix.source.remote.model.MovieCreditsDto
import com.aldyaz.movix.source.remote.model.MovieDto
import com.aldyaz.movix.source.remote.model.MoviesDto
import com.aldyaz.movix.source.remote.model.TvShowsDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class MovixRemoteServiceImpl(
    private val httpClient: HttpClient
) : MovixRemoteService {

    override suspend fun getNowPlayingMovies(): MoviesDto {
        return apiCall {
            httpClient.get("movie/now_playing")
        }
    }

    override suspend fun getPopularMovies(): MoviesDto {
        return apiCall {
            httpClient.get("movie/popular")
        }
    }

    override suspend fun getTopRatedMovies(): MoviesDto {
        return apiCall {
            httpClient.get("movie/top_rated")
        }
    }

    override suspend fun getMovieDetail(id: Long): MovieDto {
        return apiCall {
            httpClient.get("movie/$id")
        }
    }

    override suspend fun getAiringTodayTvShows(): TvShowsDto {
        return apiCall {
            httpClient.get("tv/airing_today")
        }
    }

    override suspend fun getOnTheAirTvShows(): TvShowsDto {
        return apiCall {
            httpClient.get("tv/on_the_air")
        }
    }

    override suspend fun getCredits(movieId: Long): MovieCreditsDto {
        return apiCall {
            httpClient.get("movie/$movieId/credits")
        }
    }
}