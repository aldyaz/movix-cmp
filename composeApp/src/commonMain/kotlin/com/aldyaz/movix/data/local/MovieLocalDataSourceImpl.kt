package com.aldyaz.movix.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.aldyaz.movix.database.GetFavorites
import com.aldyaz.movix.database.MovixDatabase
import com.aldyaz.movix.source.local.model.MovieDbModel
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

    override fun checkFavorite(movieId: Long): Flow<Boolean> {
        return queries.checkFavorite(movieId)
            .asFlow()
            .mapToOne(Dispatchers.IO)
    }

    override fun saveFavorite(movie: MovieDbModel) {
        queries.insertMovie(
            id = movie.id,
            title = movie.title,
            overview = movie.overview,
            original_title = movie.originalTitle,
            release_date = movie.releaseDate,
            genres = movie.genres,
            poster_path = movie.posterPath,
            backdrop_path = movie.backdropPath,
            vote_average = movie.voteAverage,
            status = movie.status,
            runtime = movie.runtime.toLong(),
            budget = movie.budget.toLong(),
            revenue = movie.revenue.toLong(),
            spoken_language = movie.languages,
            original_language = movie.originalLanguage,
        )
        queries.insertFavorite(
            id = null,
            movie_id = movie.id
        )
    }
}