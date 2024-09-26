package com.aldyaz.movix.presentation.mapper

import com.aldyaz.movix.domain.model.MovieDomainModel
import com.aldyaz.movix.presentation.model.MovieDetailPresentationModel
import com.aldyaz.movix.utils.TimeUtils
import com.aldyaz.movix.utils.round

class MovieDetailToPresentationMapper : (MovieDomainModel) -> MovieDetailPresentationModel {

    override fun invoke(p1: MovieDomainModel): MovieDetailPresentationModel {
        return MovieDetailPresentationModel(
            id = p1.id,
            title = p1.title,
            originalTitle = p1.originalTitle,
            overview = p1.overview,
            posterPath = p1.posterPath,
            backdropPath = p1.backdropPath,
            releaseDate = p1.releaseDate,
            genres = p1.genres,
            rating = p1.voteAverage.round(1),
            duration = TimeUtils.formatHourMinutes(p1.runtime)
        )
    }
}