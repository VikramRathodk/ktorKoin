package org.devvikram.ktorkoin.sqldelight

import app.cash.sqldelight.db.SqlDriver


interface DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}