package org.devvikram.ktorkoin.presentation.di

import org.devvikram.ktorkoin.presentation.viewmodels.StateViewmodel
import org.devvikram.ktorkoin.presentation.viewmodels.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val viewModuleModules = module {
    viewModel { StateViewmodel(get()) }
    viewModel{ UserViewModel(get()) }
}

actual fun shareViewmodelModule(): Module {
    return  viewModuleModules
}