package com.aldyaz.movix.domain.repository

import com.aldyaz.movix.core.domain.ResultState
import com.aldyaz.movix.domain.model.MovieDomainModel
import com.aldyaz.movix.domain.model.MovieListDomainModel

interface MovieRepository {

    suspend fun getNowPlayingMovies(): ResultState<MovieListDomainModel>

    suspend fun getPopularMovies(): ResultState<MovieListDomainModel>

    suspend fun getTopRatedMovies(): ResultState<MovieListDomainModel>

    suspend fun getMovieDetail(id: Long): ResultState<MovieDomainModel>

}
