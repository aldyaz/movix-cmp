package com.aldyaz.movix.ui.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aldyaz.movix.base.ui.component.BasicCircularLoading
import com.aldyaz.movix.base.ui.component.BasicError
import com.aldyaz.movix.base.ui.component.ScreenEnterObserver
import com.aldyaz.movix.presentation.intent.MovieDetailViewIntent
import com.aldyaz.movix.presentation.state.MovieDetailState
import com.aldyaz.movix.presentation.viewmodel.MovieDetailViewModel
import com.aldyaz.movix.ui.detail.component.DetailHeaderSection
import com.aldyaz.movix.ui.detail.component.DetailOverviewSection
import com.aldyaz.movix.ui.detail.component.GenresHorizontalScrollable
import com.aldyaz.movix.utils.KeyConst

@Composable
fun MovieDetail(
    movieId: Long,
    viewModel: MovieDetailViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScreenEnterObserver {
        viewModel.onIntent(MovieDetailViewIntent.Retry(movieId))
    }

    MovieDetailScaffold(
        uiState = state,
        onRetry = {
            viewModel.onIntent(MovieDetailViewIntent.Retry(movieId))
        },
        modifier = modifier
    )
}

@Composable
fun MovieDetailScaffold(
    uiState: MovieDetailState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold { innerPadding ->
        MovieContent(
            uiState = uiState,
            onRetryClick = onRetry,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun MovieContent(
    uiState: MovieDetailState,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        uiState.loading -> {
            BasicCircularLoading(
                modifier = modifier
            )
        }

        uiState.error -> {
            BasicError(
                modifier = modifier,
                onRetryClick = onRetryClick
            )
        }

        uiState.success -> {
            val data = uiState.movie
            LazyColumn(
                modifier = modifier,
                content = {
                    item(
                        key = KeyConst.DETAIL_HEADER_SECTION,
                        content = {
                            DetailHeaderSection(
                                title = data.title,
                                releaseDate = data.releaseDate,
                                rating = data.rating,
                                posterPath = data.posterPath,
                                backdropPath = data.backdropPath,
                                showTimeDuration = data.duration
                            )
                        }
                    )

                    item(
                        key = KeyConst.DETAIL_GENRES_SECTION,
                        content = {
                            GenresHorizontalScrollable(
                                genres = data.genres,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        vertical = 16.dp
                                    )
                            )
                        }
                    )

                    item(
                        key = KeyConst.DETAIL_OVERVIEW_SECTION,
                        content = {
                            DetailOverviewSection(
                                overview = data.overview,
                                modifier = Modifier.padding(
                                    horizontal = 16.dp
                                )
                            )
                        }
                    )
                }
            )
        }
    }
}
