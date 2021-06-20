package com.ilham.movieapplication

import android.app.Application
import com.ilham.movieapplication.core.di.databaseModule
import com.ilham.movieapplication.core.di.networkModule
import com.ilham.movieapplication.core.di.repositoryModule
import com.ilham.movieapplication.di.useCaseModule
import com.ilham.movieapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}