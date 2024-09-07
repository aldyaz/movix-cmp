package com.aldyaz.movix.presentation.state

import com.aldyaz.movix.presentation.model.MoviePresentationModel

data class DiscoverMovieState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val movies: List<MoviePresentationModel> = listOf()
) {

    companion object {
        val Initial = DiscoverMovieState()
    }
}