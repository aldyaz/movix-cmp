package com.aldyaz.movix.domain.mapper

import com.aldyaz.movix.database.Movie
import com.aldyaz.movix.domain.model.MovieDomainModel

class MovieDbToDomainMapper : (Movie) -> MovieDomainModel {

    override fun invoke(p1: Movie): MovieDomainModel {
        return MovieDomainModel(
            id = p1.id,
            title = p1.title,
            originalTitle = p1.original_title,
            overview = p1.overview,
            posterPath = p1.poster_path,
            backdropPath = p1.backdrop_path,
            releaseDate = p1.release_date,
            genres = p1.genres.split(","),
            runtime = p1.runtime.toInt(),
            voteAverage = p1.vote_average,
            languages = p1.spoken_language.split(","),
            originalLanguage = p1.original_language,
            budget = p1.budget.toInt(),
            revenue = p1.revenue.toInt(),
            status = p1.status
        )
    }
}