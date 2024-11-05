package com.aldyaz.movix.presentation.state

import com.aldyaz.movix.presentation.model.MovieItemPresentationModel

data class MainFavoriteTabState(
    val movies: List<MovieItemPresentationModel> = listOf()
) {
    companion object {
        val Initial = MainFavoriteTabState()
    }
}