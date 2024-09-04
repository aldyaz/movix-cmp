package com.aldyaz.movix.source.remote

import com.aldyaz.movix.core.network.apiCall
import com.aldyaz.movix.source.remote.model.MovieDto
import com.aldyaz.movix.source.remote.model.MoviesDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class KtorTmdbRemoteService(
    private val httpClient: HttpClient
) : TmdbRemoteService {

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
}