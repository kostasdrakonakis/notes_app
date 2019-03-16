package com.kostasdrakonakis.notes

import android.app.Application
import com.kostasdrakonakis.notes.managers.Managers
import com.kostasdrakonakis.notes.util.LogUtil

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LogUtil.initLogger()
        Managers.initData(this)
    }
}