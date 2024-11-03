package com.aldyaz.movix.presentation.intent

import com.aldyaz.movix.presentation.model.MovieDetailPresentationModel

sealed class MovieDetailViewIntent {

    data class Retry(
        val movieId: Long
    ) : MovieDetailViewIntent()

    data class OnClickFavorite(
        val movie: MovieDetailPresentationModel
    ) : MovieDetailViewIntent()

}