package com.aldyaz.movix.presentation.state

import com.aldyaz.movix.presentation.model.MovieDetailPresentationModel

data class MovieDetailState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val movie: MovieDetailPresentationModel = MovieDetailPresentationModel()
) {

    companion object {
        val Initial = MovieDetailState()
    }
}