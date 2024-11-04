@file:OptIn(ExperimentalCoroutinesApi::class)

package com.aldyaz.movix.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.aldyaz.movix.core.domain.ResultState
import com.aldyaz.movix.core.presentation.BaseViewModel
import com.aldyaz.movix.domain.interactor.CheckFavoriteMovieUseCase
import com.aldyaz.movix.domain.interactor.GetMovieDetailUseCase
import com.aldyaz.movix.domain.interactor.SaveFavoriteUseCase
import com.aldyaz.movix.presentation.intent.MovieDetailViewIntent
import com.aldyaz.movix.presentation.mapper.MovieDomainToPresentationMapper
import com.aldyaz.movix.presentation.mapper.MoviePresentationToDomainMapper
import com.aldyaz.movix.presentation.model.MovieDetailPresentationModel
import com.aldyaz.movix.presentation.state.MovieDetailState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val checkFavoriteMovieUseCase: CheckFavoriteMovieUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val movieDomainToPresentationMapper: MovieDomainToPresentationMapper,
    private val moviePresentationToDomainMapper: MoviePresentationToDomainMapper
) : BaseViewModel<MovieDetailViewIntent>() {

    private val _state = MutableStateFlow(MovieDetailState.Initial)
    private val _movieId = MutableSharedFlow<Long>(replay = 1)
    val state = _movieId.flatMapLatest { id ->
        combine(
            _state,
            flow { emit(getMovieDetailUseCase(id)) },
            checkFavoriteMovieUseCase(id)
        ) { state, movieDetailResult, isFavorite ->
            var newState = state
            newState = newState.copy(
                loading = when (movieDetailResult) {
                    is ResultState.Success,
                    is ResultState.Error -> false
                },
                error = movieDetailResult is ResultState.Error,
                movie = if (movieDetailResult is ResultState.Success) {
                    movieDomainToPresentationMapper(movieDetailResult.data)
                } else null,
                isFavorite = isFavorite
            )
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

            is MovieDetailViewIntent.OnClickFavorite -> doSaveFavorite(intent.movie)
        }
    }

    private fun doSaveFavorite(movie: MovieDetailPresentationModel) =
        viewModelScope.launch(Dispatchers.IO) {
            when (
                val result = saveFavoriteUseCase(
                    moviePresentationToDomainMapper(movie)
                )
            ) {
                is ResultState.Success -> {
                    checkFavoriteMovieUseCase(movie.id).collect { isFavorite ->
                        _state.update {
                            it.copy(
                                isFavorite = isFavorite
                            )
                        }
                    }
                }

                is ResultState.Error -> {}
            }
        }
}