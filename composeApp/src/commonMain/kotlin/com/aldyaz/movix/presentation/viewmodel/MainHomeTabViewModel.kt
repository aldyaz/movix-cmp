package com.aldyaz.movix.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.aldyaz.movix.core.presentation.BaseViewModel
import com.aldyaz.movix.core.ui.UiRefreshAction
import com.aldyaz.movix.core.ui.UiResult
import com.aldyaz.movix.core.ui.toUiResult
import com.aldyaz.movix.domain.interactor.GetNowPlayingMoviesUseCase
import com.aldyaz.movix.domain.interactor.GetPopularMoviesUseCase
import com.aldyaz.movix.domain.interactor.GetTopRatedMoviesUseCase
import com.aldyaz.movix.domain.model.MovieListDomainModel
import com.aldyaz.movix.presentation.intent.MainHomeTabViewIntent
import com.aldyaz.movix.presentation.mapper.MovieListToPresentationMapper
import com.aldyaz.movix.presentation.state.DiscoverMovieState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class MainHomeTabViewModel(
    getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    getPopularMoviesUseCase: GetPopularMoviesUseCase,
    getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val movieListToPresentationMapper: MovieListToPresentationMapper
) : BaseViewModel<MainHomeTabViewIntent>() {

    private val _nowPlayingRefresh = MutableSharedFlow<UiRefreshAction>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private val _popularRefresh = MutableSharedFlow<UiRefreshAction>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private val _topRatedRefresh = MutableSharedFlow<UiRefreshAction>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private val _nowPlayingState = MutableStateFlow(DiscoverMovieState.Initial)
    val nowPlayingState = _nowPlayingRefresh.onStart {
        emit(UiRefreshAction.Idle)
    }.flatMapLatest {
        combine(
            _nowPlayingState,
            getNowPlayingMoviesUseCase(Unit).toUiResult()
        ) { state, result ->
            (state to result).mapFinalState()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DiscoverMovieState.Initial
    )

    private val _popularState = MutableStateFlow(DiscoverMovieState.Initial)
    val popularState = _popularRefresh.onStart {
        emit(UiRefreshAction.Idle)
    }.flatMapLatest {
        combine(
            _popularState,
            getPopularMoviesUseCase(Unit).toUiResult()
        ) { state, result ->
            (state to result).mapFinalState()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DiscoverMovieState.Initial
    )

    private val _topRatedState = MutableStateFlow(DiscoverMovieState.Initial)
    val topRatedState = _topRatedRefresh.onStart {
        emit(UiRefreshAction.Idle)
    }.flatMapLatest {
        combine(
            _topRatedState,
            getTopRatedMoviesUseCase(Unit).toUiResult()
        ) { state, result ->
            (state to result).mapFinalState()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DiscoverMovieState.Initial
    )

    override fun onIntent(intent: MainHomeTabViewIntent) {
        when (intent) {
            MainHomeTabViewIntent.NowPlayingRefresh -> {
                _nowPlayingRefresh.tryEmit(UiRefreshAction.Refreshing)
            }

            MainHomeTabViewIntent.PopularRefresh -> {
                _popularRefresh.tryEmit(UiRefreshAction.Refreshing)
            }

            MainHomeTabViewIntent.TopRatedRefresh -> {
                _topRatedRefresh.tryEmit(UiRefreshAction.Refreshing)
            }
        }
    }

    private fun Pair<DiscoverMovieState, UiResult<MovieListDomainModel>>.mapFinalState(): DiscoverMovieState {
        val state = first
        val result = second
        var movies = state.movies
        val loading = result is UiResult.Loading
        val error = result is UiResult.Error
        if (result is UiResult.Success) {
            movies = movieListToPresentationMapper(result.data.movies)
        }
        return state.copy(
            loading = loading,
            error = error,
            movies = movies
        )
    }
}