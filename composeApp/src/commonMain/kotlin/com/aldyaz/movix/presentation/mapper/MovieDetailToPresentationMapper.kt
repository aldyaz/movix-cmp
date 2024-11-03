package com.aldyaz.movix.presentation.mapper

import com.aldyaz.movix.domain.model.MovieDomainModel
import com.aldyaz.movix.presentation.model.MovieDetailPresentationModel
import com.aldyaz.movix.utils.DateUtils
import com.aldyaz.movix.utils.round

class MovieDetailToPresentationMapper : (MovieDomainModel) -> MovieDetailPresentationModel {

    override fun invoke(p1: MovieDomainModel): MovieDetailPresentationModel {
        return MovieDetailPresentationModel(
            id = p1.id,
            title = "${p1.title} (${DateUtils.getYear(p1.releaseDate)})",
            overview = p1.overview,
            posterPath = p1.posterPath,
            backdropPath = p1.backdropPath,
            releaseDate = DateUtils.format(p1.releaseDate),
            genres = p1.genres,
            rating = p1.voteAverage.round(1),
            duration = p1.runtime,
            languages = p1.languages,
            originalLanguage = p1.originalLanguage,
            budget = p1.budget,
            revenue = p1.revenue,
            status = p1.status,
        )
    }
}