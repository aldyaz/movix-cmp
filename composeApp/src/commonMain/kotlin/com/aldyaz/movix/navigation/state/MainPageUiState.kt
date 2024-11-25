package com.aldyaz.movix.navigation.state

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

data class MainPageUiState(
    val eventSink: (MainPageUiEvent) -> Unit
) : CircuitUiState

sealed class MainPageUiEvent : CircuitUiEvent {

    data class OpenDetail(val movieId: Long) : MainPageUiEvent()

}
