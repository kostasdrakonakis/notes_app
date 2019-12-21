package com.kostasdrakonakis.notes.util

import com.kostasdrakonakis.notes.logger.NoteLogger
import org.slf4j.Logger

object LogUtil {

    fun initLogger() {
        NoteLogger.init()
    }

    inline fun <reified T> getLogger(): Logger {
        return NoteLogger.getInstance(T::class.java)
    }
}
