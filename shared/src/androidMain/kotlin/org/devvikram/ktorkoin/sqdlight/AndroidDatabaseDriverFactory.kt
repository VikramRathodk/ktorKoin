package org.devvikram.ktorkoin.sqdlight

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.devvikram.ktorkoin.shared.ktor_koin_db
import org.devvikram.ktorkoin.sqldelight.DatabaseDriverFactory

class AndroidDatabaseDriverFactory(private val context: Context) : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(ktor_koin_db.Schema, context, "ktor_koin_db.db")
    }
}