package com.aldyaz.movix.navigation

import com.aldyaz.movix.Parcelize
import com.slack.circuit.runtime.screen.StaticScreen

abstract class MovixScreen : StaticScreen

@Parcelize
data object MainScreen : MovixScreen()

@Parcelize
data class DetailScreen(
    val movieId: Long
) : MovixScreen()
