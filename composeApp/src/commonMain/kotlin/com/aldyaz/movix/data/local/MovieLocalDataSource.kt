package com.aldyaz.movix.data.local

import com.aldyaz.movix.database.GetFavorites
import com.aldyaz.movix.source.local.model.MovieDbModel
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {

    fun getMovies(): Flow<List<GetFavorites>>

    fun checkFavorite(movieId: Long): Flow<Boolean>

    fun saveFavorite(movie: MovieDbModel)

}