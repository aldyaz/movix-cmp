package com.aldyaz.movix.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aldyaz.movix.common.ui.component.BasicCircularLoading
import com.aldyaz.movix.common.ui.component.BasicError
import com.aldyaz.movix.common.ui.component.ScreenEnterObserver
import com.aldyaz.movix.presentation.intent.MainHomeTabViewIntent
import com.aldyaz.movix.presentation.state.DiscoverMovieState
import com.aldyaz.movix.presentation.state.MainHomeTabState
import com.aldyaz.movix.presentation.viewmodel.MainHomeTabViewModel
import com.aldyaz.movix.ui.common.component.MovieRowList
import com.aldyaz.movix.ui.common.component.MovieSectionHeader
import com.aldyaz.movix.ui.main.MainAppBar
import movixcmp.composeapp.generated.resources.Res
import movixcmp.composeapp.generated.resources.label_now_playing
import movixcmp.composeapp.generated.resources.label_popular
import movixcmp.composeapp.generated.resources.label_top_rated
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainHomeTab(
    onNavigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainHomeTabViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ScreenEnterObserver {
        viewModel.onIntent(MainHomeTabViewIntent.OnEnter)
    }
    MainHomeTabScaffold(
        uiState = uiState,
        onSearchClick = {},
        onNavigateToDetail = onNavigateToDetail,
        modifier = modifier
    )
}

@Composable
fun MainHomeTabScaffold(
    uiState: MainHomeTabState,
    onSearchClick: () -> Unit,
    onNavigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            MainAppBar(
                onSearchClick = onSearchClick,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        modifier = modifier,
        content = { contentPadding ->
            MainHomeTabContent(
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxSize(),
                uiState = uiState,
                onClickItem = onNavigateToDetail
            )
        }
    )
}

@Composable
fun MainHomeTabContent(
    uiState: MainHomeTabState,
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
