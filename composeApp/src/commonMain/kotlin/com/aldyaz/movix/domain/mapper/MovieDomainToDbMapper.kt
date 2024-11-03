package com.aldyaz.movix.domain.mapper

import com.aldyaz.movix.domain.model.MovieDomainModel
import com.aldyaz.movix.source.local.model.MovieDbModel

class MovieDomainToDbMapper : (MovieDomainModel) -> MovieDbModel {

    override fun invoke(p1: MovieDomainModel): MovieDbModel {
        return MovieDbModel(
            id = p1.id,
            title = p1.title,
            originalTitle = p1.originalTitle,
            overview = p1.overview,
            posterPath = p1.posterPath,
            backdropPath = p1.backdropPath,
            releaseDate = p1.releaseDate,
            genres = p1.genres.joinToString(","),
            runtime = p1.runtime,
            voteAverage = p1.voteAverage,
            languages = p1.languages.joinToString(","),
            originalLanguage = p1.originalLanguage,
            budget = p1.budget,
            revenue = p1.revenue,
            status = p1.status
        )
    }
}