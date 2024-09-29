package com.aldyaz.movix.utils

import com.aldyaz.movix.core.network.kamelImageLogger
import io.kamel.core.config.KamelConfig
import io.kamel.core.config.KamelConfigBuilder
import io.kamel.core.config.httpFetcher
import io.kamel.core.config.takeFrom
import io.kamel.image.config.Default

fun KamelConfigBuilder.defaultSetup() {
    takeFrom(KamelConfig.Default)
    httpFetcher {
        kamelImageLogger()
    }
}