package com.aldyaz.movix.domain.mapper

import com.aldyaz.movix.domain.model.MovieDomainModel
import com.aldyaz.movix.source.remote.model.MovieDto

class MovieToDomainMapper : (MovieDto) -> MovieDomainModel {

    override fun invoke(p1: MovieDto): MovieDomainModel {
        val genres = p1.genres.orEmpty()
        val spokenLanguages = p1.spokenLanguages.orEmpty()
        return MovieDomainModel(
            id = p1.id ?: 0L,
            title = p1.title.orEmpty(),
            originalTitle = p1.originalTitle.orEmpty(),
            overview = p1.overview.orEmpty(),
            posterPath = p1.posterPath.orEmpty(),
            backdropPath = p1.backdropPath.orEmpty(),
            releaseDate = p1.releaseDate.orEmpty(),
            genres = List(genres.size) {
                genres[it].name.orEmpty()
            },
            voteAverage = p1.voteAverage ?: 0.0,
            runtime = p1.runtime ?: 0,
            languages = List(spokenLanguages.size) {
                spokenLanguages[it].iso6391
            },
            originalLanguage = p1.originalLanguage.orEmpty(),
            budget = p1.budget ?: 0,
            revenue = p1.revenue ?: 0,
            status = p1.status.orEmpty()
        )
    }
}
