package com.aldyaz.movix.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.aldyaz.movix.core.presentation.BaseViewModel
import com.aldyaz.movix.domain.interactor.GetNowPlayingMoviesUseCase
import com.aldyaz.movix.domain.interactor.GetPopularMoviesUseCase
import com.aldyaz.movix.domain.interactor.GetTopRatedMoviesUseCase
import com.aldyaz.movix.presentation.intent.MainHomeTabViewIntent
import com.aldyaz.movix.presentation.mapper.MovieListToPresentationMapper
import com.aldyaz.movix.presentation.state.DiscoverMovieState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MainHomeTabViewModel(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val movieListToPresentationMapper: MovieListToPresentationMapper
) : BaseViewModel<MainHomeTabViewIntent>() {

    private val _nowPlayingState = MutableStateFlow(DiscoverMovieState.Initial)
    val nowPlayingState = combine(
        _nowPlayingState,
        fetchNowPlaying()
    ) { state, result ->
        state.copy(
            movies = movieListToPresentationMapper(result.movies)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DiscoverMovieState.Initial
    )

    private val _popularState = MutableStateFlow(DiscoverMovieState.Initial)
    val popularState = combine(
        _popularState,
        fetchPopular()
    ) { state, result ->
        state.copy(
            movies = movieListToPresentationMapper(result.movies)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DiscoverMovieState.Initial
    )

    private val _topRatedState = MutableStateFlow(DiscoverMovieState.Initial)
    val topRatedState = combine(
        _topRatedState,
        fetchTopRated()
    ) { state, result ->
        state.copy(
            movies = movieListToPresentationMapper(result.movies)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DiscoverMovieState.Initial
    )

    override fun onIntent(intent: MainHomeTabViewIntent) {
    }

    private fun fetchNowPlaying() = getNowPlayingMoviesUseCase(Unit)
        .onStart {
            _nowPlayingState.update {
                it.copy(
                    loading = true,
                    error = false
                )
            }
        }
        .onCompletion {
            _nowPlayingState.update {
                it.copy(
                    loading = false
                )
            }
        }
        .catch {
            _nowPlayingState.update {
                it.copy(
                    loading = false,
                    error = true
                )
            }
        }

    private fun fetchPopular() = getPopularMoviesUseCase(Unit)
        .onStart {
            _popularState.update {
                it.copy(
                    loading = true,
                    error = false
                )
            }
        }
        .onCompletion {
            _popularState.update {
                it.copy(
                    loading = false
                )
            }
        }
        .catch {
            _popularState.update {
                it.copy(
                    loading = false,
                    error = true
                )
            }
        }

    private fun fetchTopRated() = getTopRatedMoviesUseCase(Unit)
        .onStart {
            _topRatedState.update {
                it.copy(
                    loading = true,
                    error = false
                )
            }
        }
        .onCompletion {
            _topRatedState.update {
                it.copy(
                    loading = false
                )
            }
        }
        .catch {
            _topRatedState.update {
                it.copy(
                    loading = false,
                    error = true
                )
            }
        }
}