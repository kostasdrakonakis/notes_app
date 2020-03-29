package com.kostasdrakonakis.notes

import android.app.Application
import com.kostasdrakonakis.notes.di.managersModule
import com.kostasdrakonakis.notes.di.networkModule
import com.kostasdrakonakis.notes.util.LogUtil
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LogUtil.initLogger()
        startKoin {
            androidLogger()
            modules(arrayListOf(
                networkModule,
                managersModule
            ))
            androidContext(this@MainApplication)
        }
    }
}
