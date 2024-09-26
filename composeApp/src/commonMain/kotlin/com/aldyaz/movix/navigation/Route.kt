package com.aldyaz.movix.navigation

import com.aldyaz.movix.Parcelable
import com.aldyaz.movix.Parcelize
import com.slack.circuit.runtime.screen.Screen

@Parcelize
sealed class Route : Screen, Parcelable {

    data object Main : Route()

}