package com.aldyaz.movix.common.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun ScreenEnterObserver(
    onEnter: () -> Unit
) {
    var entered by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(entered) {
        if (!entered) {
            entered = true
            onEnter()
        }
    }
}