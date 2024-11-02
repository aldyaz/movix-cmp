package com.aldyaz.movix.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.aldyaz.movix.database.MovixDatabase
import com.aldyaz.movix.utils.Const

actual class SqlDelightFactory {

    actual fun sqlDriver(): SqlDriver = NativeSqliteDriver(
        schema = MovixDatabase.Schema,
        name = Const.DB_NAME
    )
}