package com.aldyaz.movix.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.aldyaz.movix.database.MovixDatabase
import database.GetFavorites
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class MovieLocalDataSourceImpl(
    movixDatabase: MovixDatabase
) : MovieLocalDataSource {

    private val queries = movixDatabase.movieQueries

    override fun getMovies(): Flow<List<GetFavorites>> {
        return queries.getFavorites()
            .asFlow()
            .mapToList(Dispatchers.IO)
    }
}