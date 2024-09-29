package com.aldyaz.movix

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.aldyaz.movix.di.initKoin
import com.aldyaz.movix.navigation.MainScreen
import com.aldyaz.movix.utils.defaultSetup
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.rememberCircuitNavigator
import io.kamel.core.config.KamelConfig

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    val kamelConfig = remember {
        KamelConfig {
            defaultSetup()
        }
    }
    val backStack = rememberSaveableBackStack(listOf(MainScreen))
    val navigator = rememberCircuitNavigator(
        backStack = backStack,
        onRootPop = {
            // No-op
        }
    )

    App(
        kamelConfig = kamelConfig,
        backStack = backStack,
        navigator = navigator
    )
}