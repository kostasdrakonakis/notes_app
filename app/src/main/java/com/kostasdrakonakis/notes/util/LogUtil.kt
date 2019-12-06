package com.kostasdrakonakis.notes.util

import com.kostasdrakonakis.notes.logger.NoteLogger
import org.slf4j.Logger

object LogUtil {

    fun initLogger() {
        NoteLogger.init()
    }

    fun getLogger(cls: Class<*>): Logger {
        return NoteLogger.getInstance(cls)
    }
}