package org.devvikram.ktorkoin.domain.di

import org.devvikram.ktorkoin.domain.usecases.StateUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        StateUseCase(
            get()
        )
    }
}