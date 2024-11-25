package com.aldyaz.movix.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aldyaz.movix.common.ui.component.BasicCircularLoading
import com.aldyaz.movix.common.ui.component.BasicError
import com.aldyaz.movix.presentation.state.DiscoverMovieState
import com.aldyaz.movix.presentation.viewmodel.MainHomeTabViewModel
import com.aldyaz.movix.ui.common.component.MovieRowList
import com.aldyaz.movix.ui.common.component.MovieSectionHeader
import com.aldyaz.movix.ui.main.MainAppBar
import com.aldyaz.movix.utils.KeyConst
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
    val nowPlayingState by viewModel.nowPlayingState.collectAsStateWithLifecycle()
    val popularState by viewModel.popularState.collectAsStateWithLifecycle()
    val topRatedState by viewModel.topRatedState.collectAsStateWithLifecycle()

    MainHomeTabScaffold(
        nowPlayingState = nowPlayingState,
        popularState = popularState,
        topRatedState = topRatedState,
        onSearchClick = {},
        onNavigateToDetail = onNavigateToDetail,
        modifier = modifier
    )
}

@Composable
fun MainHomeTabScaffold(
    nowPlayingState: DiscoverMovieState,
    popularState: DiscoverMovieState,
    topRatedState: DiscoverMovieState,
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
                nowPlayingState = nowPlayingState,
                popularState = popularState,
                topRatedState = topRatedState,
                onClickItem = onNavigateToDetail,
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxSize()
            )
        }
    )
}

@Composable
fun MainHomeTabContent(
    nowPlayingState: DiscoverMovieState,
    popularState: DiscoverMovieState,
    topRatedState: DiscoverMovieState,
    onClickItem: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(KeyConst.DISCOVER_NOW_PLAYING) {
            DiscoverSection(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(Res.string.label_now_playing),
                state = nowPlayingState,
                onClickMore = {},
                onClickItem = onClickItem
            )
        }
        item(KeyConst.DISCOVER_POPULAR) {
            DiscoverSection(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(Res.string.label_popular),
                state = popularState,
                onClickMore = {},
                onClickItem = onClickItem
            )
        }
        item(KeyConst.DISCOVER_TOP_RATED) {
            DiscoverSection(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(Res.string.label_top_rated),
                state = topRatedState,
                onClickMore = {},
                onClickItem = onClickItem
            )
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
