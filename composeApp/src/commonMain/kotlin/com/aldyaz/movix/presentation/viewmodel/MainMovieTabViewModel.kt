package com.aldyaz.movix.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.aldyaz.movix.core.coroutines.CoroutinesContextProvider
import com.aldyaz.movix.core.domain.ResultState
import com.aldyaz.movix.core.presentation.BaseViewModel
import com.aldyaz.movix.domain.interactor.GetNowPlayingMoviesUseCase
import com.aldyaz.movix.domain.interactor.GetPopularMoviesUseCase
import com.aldyaz.movix.domain.interactor.GetTopRatedMoviesUseCase
import com.aldyaz.movix.presentation.intent.MainTabViewIntent
import com.aldyaz.movix.presentation.mapper.MovieToPresentationMapper
import com.aldyaz.movix.presentation.state.DiscoverMovieState
import com.aldyaz.movix.presentation.state.MainMovieTabState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainMovieTabViewModel(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val movieToPresentationMapper: MovieToPresentationMapper,
    private val coroutinesContextProvider: CoroutinesContextProvider
) : BaseViewModel<MainTabViewIntent>() {

    private val _nowPlayingState = MutableStateFlow(DiscoverMovieState.Initial)
    private val _popularState = MutableStateFlow(DiscoverMovieState.Initial)
    private val _topRatedState = MutableStateFlow(DiscoverMovieState.Initial)

    private val _uiState = MutableStateFlow(MainMovieTabState.Initial)
    val uiState = combine(
        _uiState,
        _nowPlayingState,
        _popularState,
        _topRatedState
    ) { state, nowPlaying, popular, topRated ->
        state.copy(
            nowPlaying = nowPlaying,
            popular = popular,
            topRated = topRated
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainMovieTabState.Initial
    )

    override fun onIntent(intent: MainTabViewIntent) {
        when (intent) {
            is MainTabViewIntent.OnEnter -> {
                getNowPlaying()
                getPopular()
                getTopRated()
            }
        }
    }

    private fun getNowPlaying() = viewModelScope.launch(coroutinesContextProvider.io) {
        _nowPlayingState.update {
            it.copy(
                loading = true,
                error = false
            )
        }
        when (val result = getNowPlayingMoviesUseCase(Unit)) {
            is ResultState.Success -> {
                _nowPlayingState.update {
                    it.copy(
                        loading = false,
                        error = false,
                        movies = result.data.movies.map(movieToPresentationMapper)
                    )
                }
            }

            is ResultState.Error -> {
                _nowPlayingState.update {
                    it.copy(
                        loading = false,
                        error = true
                    )
                }
            }
        }
    }

    private fun getPopular() = viewModelScope.launch(coroutinesContextProvider.io) {
        _popularState.update {
            it.copy(
                loading = true,
                error = false
            )
        }
        when (val result = getPopularMoviesUseCase(Unit)) {
            is ResultState.Success -> {
                _popularState.update {
                    it.copy(
                        loading = false,
                        error = false,
                        movies = result.data.movies.map(movieToPresentationMapper)
                    )
                }
            }

            is ResultState.Error -> {
                _popularState.update {
                    it.copy(
                        loading = false,
                        error = true
                    )
                }
            }
        }
    }

    private fun getTopRated() = viewModelScope.launch(coroutinesContextProvider.io) {
        _topRatedState.update {
            it.copy(
                loading = true,
                error = false
            )
        }
        when (val result = getTopRatedMoviesUseCase(Unit)) {
            is ResultState.Success -> {
                _topRatedState.update {
                    it.copy(
                        loading = false,
                        error = false,
                        movies = result.data.movies.map(movieToPresentationMapper)
                    )
                }
            }

            is ResultState.Error -> {
                _topRatedState.update {
                    it.copy(
                        loading = false,
                        error = true
                    )
                }
            }
        }
    }
}