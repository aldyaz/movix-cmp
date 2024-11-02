package com.aldyaz.movix.data.local

import database.GetFavorites
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {

    fun getMovies(): Flow<List<GetFavorites>>

}