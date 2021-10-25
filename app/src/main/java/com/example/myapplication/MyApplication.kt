package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(
                webServicesModule,
                generalModule,
                networkModule,
                viewModelsModule,
                repositoriesModule
            ))
        }
    }
}