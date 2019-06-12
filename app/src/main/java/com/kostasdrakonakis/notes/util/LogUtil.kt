package com.kostasdrakonakis.notes.util

import android.app.Application
import com.kostasdrakonakis.notes.BuildConfig
import com.kostasdrakonakis.notes.logger.NoteLogger
import org.slf4j.Logger

object LogUtil {

    private const val MSG_LOG_STARTED = ("\n"
            + "************************************************\n"
            + "LOGGER STARTED\n"
            + "Package name: " + BuildConfig.APPLICATION_ID + "\n"
            + "************************************************\n")

    fun initLogger() {
        NoteLogger.init()
    }

    fun getLogger(cls: Class<*>): Logger {
        return NoteLogger.getInstance(cls)
    }

    fun logInitialData(app: Application) {
        getLogger(app::class.java).error(MSG_LOG_STARTED)
    }
}