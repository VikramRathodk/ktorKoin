package org.devvikram.ktorkoin.data.di

import org.devvikram.ktorkoin.data.db.remote.ApiService
import org.devvikram.ktorkoin.data.db.remote.KtorClient
import org.devvikram.ktorkoin.data.repositoryImp.StateRepoImp
import org.devvikram.ktorkoin.domain.repository.StateRepository
import org.koin.dsl.module

val datamodule = module {
    factory<StateRepository> { StateRepoImp(get()) }

    factory {
        ApiService(
            KtorClient.client
        )
    }
}