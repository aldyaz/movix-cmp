package com.aldyaz.movix.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.aldyaz.movix.core.presentation.BaseViewModel
import com.aldyaz.movix.domain.interactor.GetFavoriteMoviesUseCase
import com.aldyaz.movix.presentation.intent.FavoriteMainTabViewIntent
import com.aldyaz.movix.presentation.mapper.MovieListToPresentationMapper
import com.aldyaz.movix.presentation.state.FavoriteMainTabState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class MainFavoriteTabViewModel(
    private val getFavorites: GetFavoriteMoviesUseCase,
    private val movieListToPresentationMapper: MovieListToPresentationMapper
) : BaseViewModel<FavoriteMainTabViewIntent>() {

    private val _state = MutableStateFlow(FavoriteMainTabState.Initial)
    val state = combine(
        _state,
        getFavorites(Unit)
    ) { state, favorites ->
        state.copy(
            movies = movieListToPresentationMapper(favorites)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FavoriteMainTabState.Initial
    )

    override fun onIntent(intent: FavoriteMainTabViewIntent) {
    }
}