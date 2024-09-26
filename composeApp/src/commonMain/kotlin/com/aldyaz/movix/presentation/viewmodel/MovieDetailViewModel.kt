@file:OptIn(ExperimentalCoroutinesApi::class)

package com.aldyaz.movix.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.aldyaz.movix.core.domain.ResultState
import com.aldyaz.movix.core.presentation.BaseViewModel
import com.aldyaz.movix.domain.interactor.GetMovieDetailUseCase
import com.aldyaz.movix.presentation.intent.MovieDetailViewIntent
import com.aldyaz.movix.presentation.mapper.MovieDetailToPresentationMapper
import com.aldyaz.movix.presentation.state.MovieDetailState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val movieDetailToPresentationMapper: MovieDetailToPresentationMapper
) : BaseViewModel<MovieDetailViewIntent>() {

    private val _state = MutableStateFlow(MovieDetailState.Initial)
    private val _movieId = MutableSharedFlow<Long>(replay = 1)
    val state = _movieId.flatMapLatest { id ->
        combine(
            _state,
            flow { emit(getMovieDetailUseCase(id)) }
        ) { state, result ->
            var newState = state
            newState = newState.copy(
                loading = true,
                error = false
            )
            newState = when (result) {
                is ResultState.Success -> {
                    newState.copy(
                        loading = false,
                        movie = movieDetailToPresentationMapper(result.data)
                    )
                }

                is ResultState.Error -> {
                    newState.copy(
                        loading = false,
                        error = true
                    )
                }
            }
            newState
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MovieDetailState.Initial
    )

    override fun onIntent(intent: MovieDetailViewIntent) {
        when (intent) {
            is MovieDetailViewIntent.Retry -> {
                _movieId.tryEmit(intent.movieId)
            }
        }
    }
}