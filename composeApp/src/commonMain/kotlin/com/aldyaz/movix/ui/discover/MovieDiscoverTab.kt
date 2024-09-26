package com.aldyaz.movix.ui.discover

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aldyaz.movix.base.ui.component.BasicCircularLoading
import com.aldyaz.movix.base.ui.component.BasicError
import com.aldyaz.movix.base.ui.component.ScreenEnterObserver
import com.aldyaz.movix.presentation.intent.MainTabViewIntent
import com.aldyaz.movix.presentation.state.DiscoverMovieState
import com.aldyaz.movix.presentation.state.MainMovieTabState
import com.aldyaz.movix.presentation.viewmodel.MainMovieTabViewModel
import com.aldyaz.movix.ui.discover.component.MovieRowList
import com.aldyaz.movix.ui.discover.component.MovieSectionHeader
import movixcmp.composeapp.generated.resources.Res
import movixcmp.composeapp.generated.resources.label_now_playing
import movixcmp.composeapp.generated.resources.label_popular
import movixcmp.composeapp.generated.resources.label_top_rated
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieDiscoverTab(
    onNavigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainMovieTabViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ScreenEnterObserver {
        viewModel.onIntent(MainTabViewIntent.OnEnter)
    }
    MovieDiscoverTabContent(
        modifier = modifier,
        uiState = uiState,
        onClickItem = onNavigateToDetail
    )
}

@Composable
fun MovieDiscoverTabContent(
    uiState: MainMovieTabState,
    onClickItem: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            DiscoverSection(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(Res.string.label_now_playing),
                state = uiState.nowPlaying,
                onClickMore = {},
                onClickItem = onClickItem
            )
        }
        item {
            DiscoverSection(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(Res.string.label_popular),
                state = uiState.popular,
                onClickMore = {},
                onClickItem = onClickItem
            )
        }
        item {
            DiscoverSection(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(Res.string.label_top_rated),
                state = uiState.topRated,
                onClickMore = {},
                onClickItem = onClickItem
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun DiscoverSection(
    title: String,
    state: DiscoverMovieState,
    onClickMore: () -> Unit,
    onClickItem: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        MovieSectionHeader(
            title = title,
            onClickMore = onClickMore,
            modifier = Modifier.padding(
                vertical = 4.dp
            )
        )
        when {
            state.loading -> BasicCircularLoading(
                modifier = Modifier.aspectRatio(4 / 1f)
            )

            state.error -> BasicError(
                modifier = Modifier.aspectRatio(4 / 3f),
                onRetryClick = {}
            )

            else -> MovieRowList(
                items = state.movies,
                onClickItem = onClickItem
            )
        }
    }
}
