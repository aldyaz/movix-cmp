package com.aldyaz.movix.di

import app.cash.sqldelight.db.SqlDriver
import com.aldyaz.movix.core.database.SqlDelightFactory
import com.aldyaz.movix.core.network.HttpClientFactory
import com.aldyaz.movix.database.MovixDatabase
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<HttpClient> { HttpClientFactory().create() }
    single<SqlDriver> { SqlDelightFactory(get()).sqlDriver() }
    single<MovixDatabase> { MovixDatabase(get<SqlDriver>()) }
}
