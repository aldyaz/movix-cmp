package com.aldyaz.movix.core.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.aldyaz.movix.database.MovixDatabase
import com.aldyaz.movix.utils.Const

actual class SqlDelightFactory(
    private val context: Context
) {

    actual fun sqlDriver(): SqlDriver = AndroidSqliteDriver(
        MovixDatabase.Schema,
        context = context,
        name = Const.DB_NAME
    )
}