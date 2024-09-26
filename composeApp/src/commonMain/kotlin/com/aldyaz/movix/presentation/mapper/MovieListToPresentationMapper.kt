package com.aldyaz.movix.presentation.mapper

import com.aldyaz.movix.domain.model.MovieDomainModel
import com.aldyaz.movix.presentation.model.MovieItemPresentationModel

class MovieListToPresentationMapper(
    private val movieToPresentationMapper: MovieItemToPresentationMapper
) : (List<MovieDomainModel>) -> List<MovieItemPresentationModel> {

    override fun invoke(p1: List<MovieDomainModel>): List<MovieItemPresentationModel> {
        return List(p1.size) {
            movieToPresentationMapper(p1[it])
        }
    }
}