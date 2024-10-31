package com.aldyaz.movix.navigation

import com.aldyaz.movix.Parcelize
import com.slack.circuit.runtime.screen.StaticScreen

abstract class MovixScreen : StaticScreen

@Parcelize
data object MainScreen : MovixScreen()

@Parcelize
data object MainHomePage : MovixScreen()

@Parcelize
data object MainSearchPage : MovixScreen()

@Parcelize
data object MainFavoritePage : MovixScreen()

@Parcelize
data class DetailScreen(
    val movieId: Long
) : MovixScreen()
