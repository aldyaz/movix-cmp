package com.aldyaz.movix.presentation.mapper

import com.aldyaz.movix.domain.model.MovieDomainModel
import com.aldyaz.movix.presentation.model.MoviePresentationModel

class MovieListToPresentationMapper(
    private val movieToPresentationMapper: MovieToPresentationMapper
) : (List<MovieDomainModel>) -> List<MoviePresentationModel> {

    override fun invoke(p1: List<MovieDomainModel>): List<MoviePresentationModel> {
        return List(p1.size) {
            movieToPresentationMapper(p1[it])
        }
    }
}