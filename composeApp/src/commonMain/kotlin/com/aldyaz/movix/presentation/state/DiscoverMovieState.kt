package com.aldyaz.movix.presentation.state

import com.aldyaz.movix.presentation.model.MovieItemPresentationModel

data class DiscoverMovieState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val movies: List<MovieItemPresentationModel> = listOf()
) {

    companion object {
        val Initial = DiscoverMovieState()
    }
}