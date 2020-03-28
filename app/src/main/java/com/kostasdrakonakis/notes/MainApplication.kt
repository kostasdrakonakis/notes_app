package com.kostasdrakonakis.notes

import android.app.Application
import com.kostasdrakonakis.notes.di.components.DaggerAppComponent
import com.kostasdrakonakis.notes.util.LogUtil

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LogUtil.initLogger()
        DaggerAppComponent.factory().withContext(this).inject(this)
    }
}
