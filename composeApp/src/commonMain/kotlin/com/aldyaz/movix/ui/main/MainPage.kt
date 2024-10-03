package com.aldyaz.movix.ui.main

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aldyaz.movix.navigation.DetailScreen
import com.aldyaz.movix.navigation.LocalNavigator
import com.aldyaz.movix.presentation.viewmodel.MainViewModel
import com.aldyaz.movix.ui.discover.MovieDiscoverTab
import com.aldyaz.movix.ui.discover.TvDiscoverTab
import movixcmp.composeapp.generated.resources.Res
import movixcmp.composeapp.generated.resources.app_name
import movixcmp.composeapp.generated.resources.label_search_descriptor
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainPage(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.current
    val selectedTab by viewModel.selectedTab
    val tabs by remember {
        mutableStateOf(enumValues<MainTabType>())
    }

    MainScaffold(
        modifier = modifier,
        tabs = tabs,
        selectedTab = selectedTab,
        onSelectTab = viewModel::selectTab,
        onNavigateToDetail = {
            navigator.goTo(
                DetailScreen(
                    movieId = it
                )
            )
        }
    )
}

@Composable
private fun MainScaffold(
    tabs: Array<MainTabType>,
    selectedTab: MainTabType,
    onSelectTab: (MainTabType) -> Unit,
    onNavigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            MainAppBar(
                onSearchClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(
                        WindowInsets.systemBars.only(
                            WindowInsetsSides.Top
                        )
                    )
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
                                onNavigateToDetail = onNavigateToDetail,
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

@Composable
fun MainAppBar(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
    ) {
        Card(
            onClick = { onSearchClick() },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(Res.string.app_name),
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(Res.string.label_search_descriptor)
                )
            }
        }
    }
}

@Composable
fun MainNavigationBar(
    tabs: Array<MainTabType>,
    selected: (MainTabType) -> Boolean,
    onSelectTab: (MainTabType) -> Unit
) {
    NavigationBar {
        tabs.forEach { type ->
            NavigationBarItem(
                selected = selected(type),
                onClick = {
                    onSelectTab(type)
                },
                icon = {
                    Icon(
                        imageVector = type.icon,
                        contentDescription = type.title
                    )
                },
                label = {
                    androidx.compose.material3.Text(type.title)
                },
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}
