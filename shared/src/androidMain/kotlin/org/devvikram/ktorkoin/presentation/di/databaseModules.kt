package org.devvikram.ktorkoin.presentation.di

import org.devvikram.ktorkoin.sqdlight.AndroidDatabaseDriverFactory
import org.devvikram.ktorkoin.sqldelight.DatabaseDriverFactory
import org.koin.dsl.module


val databaseModule = module {
    single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(get()) }

}