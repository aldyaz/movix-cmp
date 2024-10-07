package com.aldyaz.movix.presentation.state

import com.aldyaz.movix.presentation.model.MovieDetailPresentationModel

data class MovieDetailState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val movie: MovieDetailPresentationModel? = null
) {

    val success = !loading && !error

    companion object {
        val Initial = MovieDetailState()
    }
}