package com.aldyaz.movix.data.local

import com.aldyaz.movix.database.Movie
import com.aldyaz.movix.source.local.model.MovieDbModel
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {

    fun getMovies(): Flow<List<Movie>>

    fun checkFavorite(movieId: Long): Flow<Boolean>

    fun saveFavorite(movie: MovieDbModel)

}