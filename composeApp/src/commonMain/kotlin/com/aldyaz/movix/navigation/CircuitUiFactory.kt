package com.aldyaz.movix.navigation

import com.aldyaz.movix.ui.detail.MovieDetail
import com.aldyaz.movix.ui.main.MainPage
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import org.koin.compose.viewmodel.koinViewModel

class CircuitUiFactory : Ui.Factory {

    override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
        return when (screen) {
            is MainScreen -> ui<CircuitUiState> { _, modifier ->
                MainPage(
                    viewModel = koinViewModel(),
                    modifier = modifier
                )
            }

            is DetailScreen -> ui<CircuitUiState> { _, modifier ->
                MovieDetail(
                    movieId = screen.movieId,
                    viewModel = koinViewModel(),
                    modifier = modifier
                )
            }

            else -> null
        }
    }
}