package com.aldyaz.movix.navigation

import com.aldyaz.movix.ui.favorite.MainFavoriteTab
import com.aldyaz.movix.ui.home.MainHomeTab
import com.aldyaz.movix.ui.search.MainSearchTab
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui

class MainPageUiFactory(
    private val onNavigateToDetail: (Long) -> Unit
) : Ui.Factory {

    override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
        return when (screen) {
            is MainScreen.HomeTab -> ui<CircuitUiState> { _, modifier ->
                MainHomeTab(
                    onNavigateToDetail = onNavigateToDetail,
                    modifier = modifier
                )
            }

            is MainScreen.SearchTab -> ui<CircuitUiState> { _, modifier ->
                MainSearchTab(modifier = modifier)
            }

            is MainScreen.FavoriteTab -> ui<CircuitUiState> { _, modifier ->
                MainFavoriteTab(modifier = modifier)
            }

            else -> null
        }
    }
}