package com.aldyaz.movix.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

actual class HttpClientFactory {

    actual fun create(): HttpClient {
        return HttpClient(Darwin) {
            defaultContentNegotiation()
            defaultLogging()
            defaultHttpRequest()
        }
    }
}