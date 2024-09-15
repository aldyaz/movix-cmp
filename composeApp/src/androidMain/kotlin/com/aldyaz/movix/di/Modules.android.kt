package com.aldyaz.movix.di

import com.aldyaz.movix.core.network.HttpClientFactory
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<HttpClient> { HttpClientFactory().create() }
}
