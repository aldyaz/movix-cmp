package com.aldyaz.movix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.aldyaz.movix.core.network.kamelImageLogger
import com.aldyaz.movix.navigation.MainScreen
import com.aldyaz.movix.utils.defaultSetup
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.rememberCircuitNavigator
import io.kamel.core.config.KamelConfig
import io.kamel.core.config.httpFetcher
import io.kamel.core.config.takeFrom
import io.kamel.image.config.Default
import io.kamel.image.config.resourcesFetcher
import io.kamel.image.config.resourcesIdMapper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val kamelConfig = remember {
                KamelConfig {
                    defaultSetup()
                    resourcesFetcher(context)
                    resourcesIdMapper(context)
                }
            }
            val backStack = rememberSaveableBackStack(listOf(MainScreen))
            val navigator = rememberCircuitNavigator(backStack)

            App(
                kamelConfig = kamelConfig,
                backStack = backStack,
                navigator = navigator
            )
        }
    }
}
