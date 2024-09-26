package com.aldyaz.movix

import androidx.compose.runtime.Composable
import com.aldyaz.movix.navigation.Route
import com.aldyaz.movix.presentation.viewmodel.MainViewModel
import com.aldyaz.movix.ui.main.MainPage
import com.aldyaz.movix.ui.theme.AppTheme
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuitx.gesturenavigation.GestureNavigationDecoration
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    AppTheme {
        AppNavigation()
    }
}

@Composable
fun AppNavigation() {
    val backStack = rememberSaveableBackStack(root = Route.Main)
    val navigator = rememberCircuitNavigator(backStack) { result ->
        // TODO: On quit
    }
    val circuit = Circuit.Builder()
        .setOnUnavailableContent { screen, modifier ->
            when (screen) {
                is Route.Main -> {
                    val viewModel: MainViewModel = koinViewModel()
                    MainPage(
                        viewModel = viewModel,
                        modifier = modifier
                    )
                }

                is Route.Detail -> {
                }
            }
        }
        .build()

    CircuitCompositionLocals(circuit) {
        NavigableCircuitContent(
            navigator = navigator,
            backStack = backStack,
            decoration = GestureNavigationDecoration {
                navigator.pop()
            }
        )
    }
}
