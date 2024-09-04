package com.aldyaz.movix.source.remote

import com.aldyaz.movix.source.remote.model.MovieDto
import com.aldyaz.movix.source.remote.model.MoviesDto

interface TmdbRemoteService {

    suspend fun getNowPlayingMovies(): MoviesDto

    suspend fun getPopularMovies(): MoviesDto

    suspend fun getTopRatedMovies(): MoviesDto

    suspend fun getMovieDetail(id: Long): MovieDto

}