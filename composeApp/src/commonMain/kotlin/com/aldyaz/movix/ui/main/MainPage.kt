package com.aldyaz.movix.ui.main

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.aldyaz.movix.base.ui.component.MainAppBar
import com.aldyaz.movix.presentation.viewmodel.MainViewModel
import com.aldyaz.movix.ui.discover.MovieDiscoverTab
import com.aldyaz.movix.ui.discover.TvDiscoverTab

@Composable
fun MainPage(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val selectedTab by viewModel.selectedTab
    val tabs by remember {
        mutableStateOf(enumValues<MainTabType>())
    }

    MainScaffold(
        modifier = modifier,
        tabs = tabs,
        selectedTab = selectedTab,
        onSelectTab = viewModel::selectTab
    )
}

@Composable
private fun MainScaffold(
    tabs: Array<MainTabType>,
    selectedTab: MainTabType,
    onSelectTab: (MainTabType) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            MainAppBar(
                onSearchClick = {}
            )
        },
        bottomBar = {
            MainNavigationBar(
                tabs = tabs,
                selected = { it == selectedTab },
                onSelectTab = onSelectTab
            )
        },
        content = { contentPadding ->
            val contentModifier = modifier.padding(contentPadding)
            Crossfade(
                targetState = selectedTab,
                label = selectedTab.title,
                content = { type ->
                    when (type) {
                        MainTabType.MOVIE -> {
                            MovieDiscoverTab(
                                onNavigateToDetail = {},
                                modifier = contentModifier
                            )
                        }

                        MainTabType.TV -> {
                            TvDiscoverTab(
                                modifier = contentModifier
                            )
                        }
                    }
                }
            )
        }
    )
}
