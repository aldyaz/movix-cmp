package com.aldyaz.movix.presentation.intent

sealed class MovieDetailViewIntent {

    data class Retry(
        val movieId: Long
    ) : MovieDetailViewIntent()

}