package com.aldyaz.movix.presentation.mapper

import com.aldyaz.movix.domain.model.MovieDomainModel
import com.aldyaz.movix.presentation.model.MovieItemPresentationModel
import com.aldyaz.movix.utils.round

class MovieItemToPresentationMapper : (MovieDomainModel) -> MovieItemPresentationModel {

    override fun invoke(p1: MovieDomainModel): MovieItemPresentationModel {
        return MovieItemPresentationModel(
            id = p1.id,
            title = p1.title,
            posterPath = p1.posterPath,
            rating = p1.voteAverage.round(1),
            ratingStar = p1.voteAverage.toFloat().div(2)
        )
    }
}