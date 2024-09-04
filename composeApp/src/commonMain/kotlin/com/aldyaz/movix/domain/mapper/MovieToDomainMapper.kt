package com.aldyaz.movix.domain.mapper

import com.aldyaz.movix.domain.model.MovieDomainModel
import com.aldyaz.movix.source.remote.model.MovieDto

class MovieToDomainMapper : (MovieDto) -> MovieDomainModel {

    override fun invoke(p1: MovieDto): MovieDomainModel {
        return MovieDomainModel(
            id = p1.id ?: 0L,
            title = p1.title.orEmpty(),
            originalTitle = p1.originalTitle.orEmpty(),
            overview = p1.overview.orEmpty(),
            posterPath = p1.posterPath.orEmpty(),
            backdropPath = p1.backdropPath.orEmpty(),
            releaseDate = p1.releaseDate.orEmpty(),
            genres = p1.genres?.map {
                it.name.orEmpty()
            }.orEmpty(),
            voteAverage = p1.voteAverage ?: 0.0,
            runtime = p1.runtime ?: 0
        )
    }
}
