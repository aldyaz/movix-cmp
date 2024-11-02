package com.aldyaz.movix.domain.repository

import com.aldyaz.movix.core.domain.ResultState
import com.aldyaz.movix.domain.model.MovieCastDomainModel
import com.aldyaz.movix.domain.model.MovieDomainModel
import com.aldyaz.movix.domain.model.MovieListDomainModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getNowPlayingMovies(): ResultState<MovieListDomainModel>

    suspend fun getPopularMovies(): ResultState<MovieListDomainModel>

    suspend fun getTopRatedMovies(): ResultState<MovieListDomainModel>

    suspend fun getMovieDetail(id: Long): ResultState<MovieDomainModel>

    suspend fun getCredits(movieId: Long): ResultState<List<MovieCastDomainModel>>

    fun getFavoriteMovies(): Flow<List<MovieDomainModel>>

}
