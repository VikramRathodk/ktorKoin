package org.devvikram.ktorkoin.data.di

import org.devvikram.ktorkoin.data.db.remote.ApiService
import org.devvikram.ktorkoin.data.db.remote.KtorClient
import org.devvikram.ktorkoin.data.repositoryImp.StateRepoImp
import org.devvikram.ktorkoin.domain.repository.StateRepository
import org.devvikram.ktorkoin.sqldelight.DatabaseDriverFactory
import org.devvikram.ktorkoin.sqldelight.UserRepository
import org.koin.dsl.module

val datamodule = module {
    factory<StateRepository> { StateRepoImp(get()) }
    single { UserRepository(get<DatabaseDriverFactory>().createDriver()) }

    factory {
        ApiService(
            KtorClient.client
        )
    }
}