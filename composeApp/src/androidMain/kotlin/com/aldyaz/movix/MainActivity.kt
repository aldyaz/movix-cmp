package com.aldyaz.movix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aldyaz.movix.navigation.MainScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.rememberCircuitNavigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val backStack = rememberSaveableBackStack(listOf(MainScreen))
            val navigator = rememberCircuitNavigator(backStack)

            App(
                backStack = backStack,
                navigator = navigator
            )
        }
    }
}
