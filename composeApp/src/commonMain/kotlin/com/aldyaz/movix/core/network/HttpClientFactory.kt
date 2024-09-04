package com.aldyaz.movix.core.network

import io.ktor.client.HttpClient

expect class HttpClientFactory {

    fun create(): HttpClient

}