package com.aldyaz.movix.presentation.mapper

import com.aldyaz.movix.domain.model.MovieDomainModel
import com.aldyaz.movix.presentation.model.MoviePresentationModel
import com.aldyaz.movix.utils.TimeUtils

class MovieToPresentationMapper : (MovieDomainModel) -> MoviePresentationModel {

    override fun invoke(p1: MovieDomainModel): MoviePresentationModel {
        return MoviePresentationModel(
            id = p1.id,
            title = p1.title,
            originalTitle = p1.originalTitle,
            overview = p1.overview,
            posterPath = p1.posterPath,
            backdropPath = p1.backdropPath,
            releaseDate = p1.releaseDate,
            genres = p1.genres,
            rating = p1.voteAverage.toFloat().div(2),
            duration = TimeUtils.formatHourMinutes(p1.runtime)
        )
    }
}