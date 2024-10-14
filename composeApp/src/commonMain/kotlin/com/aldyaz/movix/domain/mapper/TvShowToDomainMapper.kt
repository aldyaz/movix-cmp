package com.aldyaz.movix.domain.mapper

import com.aldyaz.movix.domain.model.TvShowDomainModel
import com.aldyaz.movix.source.remote.model.TvShowDto

class TvShowToDomainMapper : (TvShowDto) -> TvShowDomainModel {

    override fun invoke(p1: TvShowDto): TvShowDomainModel {
        return TvShowDomainModel(
            id = p1.id ?: 0L,
            adult = p1.adult ?: false,
            backdropPath = p1.backdropPath.orEmpty(),
            genreIds = p1.genreIds.orEmpty(),
            originCountry = p1.originCountry.orEmpty(),
            originalLanguage = p1.originalLanguage.orEmpty(),
            originalName = p1.originalName.orEmpty(),
            overview = p1.overview.orEmpty(),
            popularity = p1.popularity ?: 0.0,
            posterPath = p1.posterPath.orEmpty(),
            firstAirDate = p1.firstAirDate.orEmpty(),
            name = p1.name.orEmpty(),
            voteAverage = p1.voteAverage ?: 0.0,
            voteCount = p1.voteCount ?: 0
        )
    }
}