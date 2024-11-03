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
import com.aldyaz.movix.ui.detail.component.BackdropPoster
import com.aldyaz.movix.ui.detail.component.GeneralInformationSection
import com.aldyaz.movix.ui.detail.component.GenresHorizontalScrollable
import com.aldyaz.movix.ui.detail.component.OverviewSection
import com.aldyaz.movix.ui.detail.component.RatingSection
import com.aldyaz.movix.ui.detail.component.TitleSection
import com.aldyaz.movix.utils.KeyConst
import movixcmp.composeapp.generated.resources.Res
import movixcmp.composeapp.generated.resources.label_budget
import movixcmp.composeapp.generated.resources.label_minutes
import movixcmp.composeapp.generated.resources.label_original_language
import movixcmp.composeapp.generated.resources.label_released_status
import movixcmp.composeapp.generated.resources.label_revenue
import org.jetbrains.compose.resources.stringResource

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
                title = uiState.movie?.title.orEmpty(),
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
            if (data != null) {
                LazyColumn(
                    modifier = modifier,
                    contentPadding = contentPadding,
                    content = {
                        item(
                            key = KeyConst.DETAIL_BACKDROP_SECTION,
                            content = {
                                BackdropPoster(
                                    path = data.backdropPath
                                )
                            }
                        )

                        item(
                            key = KeyConst.DETAIL_TITLE_SECTION,
                            content = {
                                TitleSection(
                                    title = data.title,
                                    favorite = uiState.isFavorite,
                                    onClickFavorite = {},
                                    modifier = Modifier
                                        .padding(
                                            top = 16.dp,
                                            start = 16.dp,
                                            end = 16.dp,
                                            bottom = 8.dp
                                        )
                                        .fillMaxWidth()
                                )
                            }
                        )

                        item(
                            key = KeyConst.DETAIL_RATING_SECTION,
                            content = {
                                RatingSection(
                                    rating = data.rating,
                                    releaseDate = data.releaseDate,
                                    showTimeDuration = "${data.duration} " +
                                        stringResource(Res.string.label_minutes),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
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
                                            vertical = 8.dp
                                        )
                                )
                            }
                        )

                        item(
                            key = KeyConst.DETAIL_OVERVIEW_SECTION,
                            content = {
                                OverviewSection(
                                    overview = data.overview,
                                    modifier = Modifier.padding(
                                        vertical = 8.dp,
                                        horizontal = 16.dp
                                    )
                                )
                            }
                        )

                        item(
                            key = KeyConst.DETAIL_GENERAL_SECTION,
                            content = {
                                GeneralInformationSection(
                                    originalLanguage = Pair(
                                        stringResource(Res.string.label_original_language),
                                        data.originalLanguage
                                    ),
                                    budget = Pair(
                                        stringResource(Res.string.label_budget),
                                        data.budget.toString()
                                    ),
                                    revenue = Pair(
                                        stringResource(Res.string.label_revenue),
                                        data.revenue.toString()
                                    ),
                                    releasedStatus = Pair(
                                        stringResource(Res.string.label_released_status),
                                        data.status
                                    ),
                                    modifier = Modifier
                                        .padding(
                                            vertical = 12.dp,
                                            horizontal = 16.dp
                                        )
                                        .fillMaxWidth()
                                )
                            }
                        )

                        item(
                            key = KeyConst.DETAIL_SPACER,
                            content = {
                                Spacer(
                                    modifier = Modifier
                                        .height(300.dp)
                                        .fillParentMaxWidth()
                                )
                            }
                        )
                    }
                )
            }
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
