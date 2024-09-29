package com.aldyaz.movix.core.network

import com.aldyaz.movix.BuildConfig
import com.aldyaz.movix.constants.NetworkConst
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun <T : HttpClientEngineConfig> HttpClientConfig<T>.defaultContentNegotiation() {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                useAlternativeNames = true
                ignoreUnknownKeys = true
                encodeDefaults = false
            }
        )
    }
}

fun <T : HttpClientEngineConfig> HttpClientConfig<T>.defaultLogging(
    onLog: ((String) -> Unit)? = null
) {
    Logging {
        logger = object : Logger {
            override fun log(message: String) {
                onLog?.invoke(message)
            }
        }
        level = LogLevel.ALL
    }
}

fun HttpClientConfig<*>.defaultHttpRequest() {
    defaultRequest {
        contentType(ContentType.Application.Json)
        accept(ContentType.Application.Json)
        url {
            takeFrom(NetworkConst.BASE_URL)
            parameters.append(NetworkConst.API_KEY_PATH, BuildConfig.API_KEY)
        }
    }
}

fun HttpClientConfig<*>.kamelImageLogger() {
    Logging {
        logger = Logger.SIMPLE
        level = LogLevel.INFO
    }
}
