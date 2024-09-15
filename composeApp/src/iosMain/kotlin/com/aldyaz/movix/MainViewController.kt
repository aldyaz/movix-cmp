package com.aldyaz.movix

import androidx.compose.ui.window.ComposeUIViewController
import com.aldyaz.movix.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}