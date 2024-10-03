@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)

package com.aldyaz.movix.ui.detail

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aldyaz.movix.common.ui.component.ArrowBackPlatform
import com.aldyaz.movix.common.ui.component.BasicCircularLoading
import com.aldyaz.movix.common.ui.component.BasicError
import com.aldyaz.movix.common.ui.component.ScreenEnterObserver
import com.aldyaz.movix.navigation.LocalNavigator
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
    val navigator = LocalNavigator.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScreenEnterObserver {
        viewModel.onIntent(MovieDetailViewIntent.Retry(movieId))
    }

    MovieDetailScaffold(
        uiState = state,
        onClickBack = navigator::pop,
        onRetry = {
            viewModel.onIntent(MovieDetailViewIntent.Retry(movieId))
        },
        modifier = modifier
    )
}

@Composable
fun MovieDetailScaffold(
    uiState: MovieDetailState,
    onClickBack: () -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    val layoutDirection = LocalLayoutDirection.current
    val appBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            MovieDetailAppBar(
                scrollBehavior = appBarScrollBehavior,
                onClickBack = onClickBack,
                title = uiState.movie.title,
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = modifier.nestedScroll(
            connection = appBarScrollBehavior.nestedScrollConnection
        ),
        content = { contentPadding ->
            MovieContent(
                uiState = uiState,
                onRetryClick = onRetry,
                contentPadding = PaddingValues(
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    top = 0.dp,
                    end = contentPadding.calculateEndPadding(layoutDirection),
                    bottom = contentPadding.calculateBottomPadding()
                ),
                modifier = Modifier.fillMaxSize()
            )
        }
    )
}

@Composable
fun MovieContent(
    uiState: MovieDetailState,
    contentPadding: PaddingValues,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        uiState.loading -> {
            BasicCircularLoading(
                modifier = Modifier
                    .padding(contentPadding)
                    .then(modifier)
            )
        }

        uiState.error -> {
            BasicError(
                onRetryClick = onRetryClick,
                modifier = Modifier
                    .padding(contentPadding)
                    .then(modifier)
            )
        }

        uiState.success -> {
            val data = uiState.movie
            LazyColumn(
                modifier = modifier,
                contentPadding = contentPadding,
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

                    item {
                        Spacer(
                            modifier = Modifier
                                .height(300.dp)
                                .fillParentMaxWidth()
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun MovieDetailAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null
) {
    TopAppBar(
        title = {
            if (title != null && scrollBehavior.state.contentOffset < -1f) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = onClickBack,
                content = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBackPlatform,
                        contentDescription = null
                    )
                }
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0f),
            scrolledContainerColor = MaterialTheme.colorScheme.surface
        ),
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}
