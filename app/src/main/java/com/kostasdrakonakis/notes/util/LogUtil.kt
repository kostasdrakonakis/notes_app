package com.kostasdrakonakis.notes.util

import com.kostasdrakonakis.notes.logger.NoteLogger

object LogUtil {

    fun initLogger() {
        NoteLogger.init()
    }

    inline fun <reified T> logger(): NoteLogger {
        return NoteLogger.getInstance(T::class.java)
    }
}
