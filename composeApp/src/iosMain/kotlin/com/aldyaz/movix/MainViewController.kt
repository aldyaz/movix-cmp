package com.aldyaz.movix

import androidx.compose.ui.window.ComposeUIViewController
import com.aldyaz.movix.di.initKoin
import com.aldyaz.movix.navigation.MainScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.rememberCircuitNavigator

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    val backStack = rememberSaveableBackStack(listOf(MainScreen))
    val navigator = rememberCircuitNavigator(
        backStack = backStack,
        onRootPop = {
            // No-op
        }
    )

    App(
        backStack = backStack,
        navigator = navigator
    )
}