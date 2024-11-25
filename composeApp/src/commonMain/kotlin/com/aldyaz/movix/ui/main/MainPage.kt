package com.aldyaz.movix.ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aldyaz.movix.navigation.DetailScreen
import com.aldyaz.movix.navigation.LocalNavigator
import com.aldyaz.movix.navigation.MainPageUiFactory
import com.aldyaz.movix.navigation.MainScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.popUntil
import com.slack.circuit.runtime.screen.Screen
import movixcmp.composeapp.generated.resources.Res
import movixcmp.composeapp.generated.resources.app_name
import movixcmp.composeapp.generated.resources.label_search_descriptor
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainPage(
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.current
    val tabs by remember {
        mutableStateOf(enumValues<MainTabType>())
    }

    MainScaffold(
        tabs = tabs,
        onNavigateToDetail = {
            navigator.goTo(
                DetailScreen(
                    movieId = it
                )
            )
        },
        modifier = modifier
    )
}

@Composable
private fun MainScaffold(
    tabs: Array<MainTabType>,
    onNavigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiFactory = remember {
        MainPageUiFactory(
            onNavigateToDetail = onNavigateToDetail
        )
    }
    val circuit = Circuit.Builder()
        .addUiFactory(uiFactory)
        .build()
    val backStack = rememberSaveableBackStack(root = MainScreen.HomeTab)
    val mainNavigator = rememberCircuitNavigator(
        backStack = backStack,
        onRootPop = {}
    )
    val rootScreen by remember(backStack) {
        derivedStateOf {
            backStack.last().screen
        }
    }

    Scaffold(
        bottomBar = {
            MainNavigationBar(
                tabs = tabs,
                selected = {
                    it.screen == rootScreen
                },
                onSelectTab = {
                    mainNavigator.resetRootIfDifferent(
                        screen = it.screen,
                        saveState = true,
                        restoreState = true
                    )
                }
            )
        },
        content = { contentPadding ->

            val contentModifier = modifier
                .padding(contentPadding)
                .fillMaxSize()

            CircuitCompositionLocals(
                circuit = circuit,
                content = {
                    NavigableCircuitContent(
                        navigator = mainNavigator,
                        backStack = backStack,
                        modifier = contentModifier
                    )
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

private fun Navigator.resetRootIfDifferent(
    screen: Screen,
    saveState: Boolean = false,
    restoreState: Boolean = false,
) {
    val backStack = peekBackStack()

    if (backStack.lastOrNull() == screen) {
        Snapshot.withMutableSnapshot {
            popUntil { peekBackStack().size == 1 }
        }
    } else {
        resetRoot(screen, saveState, restoreState)
    }
}
