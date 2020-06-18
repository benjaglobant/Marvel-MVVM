package com.globant.marvelmvvm

import android.app.Application
import com.facebook.stetho.Stetho
import com.globant.di.dataBaseModule
import com.globant.di.repositoryModule
import com.globant.di.servicesModule
import com.globant.di.useCasesModule
import com.globant.marvelmvvm.di.modelsModule
import com.globant.marvelmvvm.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        startKoin {
            androidContext(this@SampleApplication)
            modules(listOf(viewModelsModule, modelsModule, servicesModule, useCasesModule, dataBaseModule, repositoryModule))
        }
    }
}
