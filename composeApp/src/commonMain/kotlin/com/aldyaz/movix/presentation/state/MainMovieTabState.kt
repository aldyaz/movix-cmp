package com.aldyaz.movix.presentation.state

data class MainMovieTabState(
    val nowPlaying: DiscoverMovieState = DiscoverMovieState.Initial,
    val popular: DiscoverMovieState = DiscoverMovieState.Initial,
    val topRated: DiscoverMovieState = DiscoverMovieState.Initial
) {

    companion object {
        val Initial = MainMovieTabState()
    }
}
