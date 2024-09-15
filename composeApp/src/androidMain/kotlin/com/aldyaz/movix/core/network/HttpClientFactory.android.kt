package com.aldyaz.movix.core.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android

actual class HttpClientFactory {

    actual fun create(): HttpClient {
        return HttpClient(Android) {
            defaultContentNegotiation()
            defaultLogging { message ->
                Log.v(this::class.java.canonicalName, message)
            }
            defaultHttpRequest()
        }
    }
}