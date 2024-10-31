package com.aldyaz.movix

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.aldyaz.movix.navigation.LocalNavigator
import com.aldyaz.movix.navigation.MovixNavigator
import com.aldyaz.movix.ui.theme.AppTheme
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.runtime.Navigator
import com.slack.circuitx.gesturenavigation.GestureNavigationDecoration
import io.kamel.core.config.KamelConfig
import io.kamel.image.config.LocalKamelConfig
import org.koin.compose.koinInject

@Composable
fun App(
    kamelConfig: KamelConfig,
    backStack: SaveableBackStack,
    navigator: Navigator
) {

    val circuit: Circuit = koinInject()
    val movixNavigator = remember(navigator) {
        MovixNavigator(navigator)
    }

    CompositionLocalProvider(
        LocalKamelConfig provides kamelConfig,
        LocalNavigator provides movixNavigator
    ) {
        CircuitCompositionLocals(circuit) {
            AppTheme {
                AppContent(
                    navigator = movixNavigator,
                    backStack = backStack
                )
            }
        }
    }
}

@Composable
fun AppContent(
    navigator: Navigator,
    backStack: SaveableBackStack
) {
    NavigableCircuitContent(
        navigator = navigator,
        backStack = backStack,
        decoration = remember(navigator) {
            GestureNavigationDecoration {
                navigator.pop()
            }
        },
        modifier = Modifier
            .safeDrawingPadding()
    )
}
