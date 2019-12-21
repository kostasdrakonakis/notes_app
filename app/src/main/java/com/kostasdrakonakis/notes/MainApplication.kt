package com.kostasdrakonakis.notes

import android.app.Application
import com.kostasdrakonakis.notes.util.LogUtil
import com.kostasdrakonakis.notes.viewmodels.ViewModels

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LogUtil.initLogger()
        ViewModels.init(this)
    }
}
