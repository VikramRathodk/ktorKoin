package org.devvikram.ktorkoin

import android.app.Application
import org.devvikram.ktorkoin.data.di.datamodule
import org.devvikram.ktorkoin.domain.di.domainModule
import org.devvikram.ktorkoin.presentation.di.databaseModule
import org.devvikram.ktorkoin.presentation.di.shareViewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@BaseApplication)
            modules(databaseModule + datamodule + domainModule + shareViewmodelModule())
        }
    }

}