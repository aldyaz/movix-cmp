package com.aldyaz.movix.core.database

import app.cash.sqldelight.db.SqlDriver

expect class SqlDelightFactory {

    fun sqlDriver(): SqlDriver

}