package com.aldyaz.movix.base.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ScreenEnterObserver(
    onEnter: () -> Unit
) {
    var entered by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(entered) {
        if (!entered) {
            entered = true
            onEnter()
        }
    }
}