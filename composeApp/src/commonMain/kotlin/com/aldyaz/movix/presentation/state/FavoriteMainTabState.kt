package com.aldyaz.movix.presentation.state

import com.aldyaz.movix.presentation.model.MovieItemPresentationModel

data class FavoriteMainTabState(
    val movies: List<MovieItemPresentationModel> = listOf()
) {
    companion object {
        val Initial = FavoriteMainTabState()
    }
}