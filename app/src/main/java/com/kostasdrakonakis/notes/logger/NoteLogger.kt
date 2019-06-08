package com.kostasdrakonakis.notes.logger

import com.kostasdrakonakis.notes.BuildConfig
import org.slf4j.Logger
import org.slf4j.Marker
import org.slf4j.helpers.MarkerIgnoringBase
import timber.log.Timber

class NoteLogger constructor(private val cls: Class<*>) : MarkerIgnoringBase(), Logger {

    companion object {
        fun init() {
            Timber.plant(Timber.DebugTree())
        }

        fun getInstance(cls: Class<*>): NoteLogger {
            return NoteLogger(cls)
        }
    }

    override fun isErrorEnabled(): Boolean {
        return true
    }

    override fun isDebugEnabled(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun isInfoEnabled(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun isWarnEnabled(): Boolean {
        return true
    }

    override fun isTraceEnabled(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun trace(message: String) {
        trace(message, null as Throwable?)
    }

    override fun trace(format: String, arg: Any) {
        tag()
        Timber.v(format, arg)
    }

    override fun trace(format: String, arg1: Any, arg2: Any) {
        tag()
        Timber.v(format, arg1, arg2)
    }

    override fun trace(format: String, vararg arguments: Any) {
        tag()
        Timber.v(format, arguments)
    }

    override fun trace(message: String, throwable: Throwable?) {
        tag()
        if (throwable == null) {
            Timber.v(message)
        } else {
            Timber.v(throwable, message)
        }
    }

    override fun trace(marker: Marker?, msg: String) {
        throw IllegalArgumentException("Not implemented yet")
    }

    override fun trace(marker: Marker?, format: String, arg: Any) {
        throw IllegalArgumentException("Not implemented yet")
    }

    override fun trace(marker: Marker?, format: String, arg1: Any, arg2: Any) {
        throw IllegalArgumentException("Not implemented yet")
    }

    override fun trace(marker: Marker?, format: String, vararg argArray: Any) {
        throw IllegalArgumentException("Not implemented yet")
    }

    override fun trace(marker: Marker?, msg: String, t: Throwable) {
        throw IllegalArgumentException("Not implemented yet")
    }

    override fun warn(message: String) {
        warn(message, null as Throwable?)
    }

    override fun warn(format: String, arg: Any) {
        tag()
        Timber.w(format, arg)
    }

    override fun warn(format: String, vararg arguments: Any) {
        tag()
        Timber.w(format, arguments)
    }

    override fun warn(format: String, arg1: Any, arg2: Any) {
        tag()
        Timber.w(format, arg1, arg2)
    }

    override fun warn(message: String, throwable: Throwable?) {
        tag()
        if (throwable == null) {
            Timber.w(message)
        } else {
            Timber.w(throwable, message)
        }
    }

    override fun debug(message: String) {
        debug(message, null as Throwable?)
    }

    override fun debug(format: String, arg: Any) {
        tag()
        Timber.d(format, arg)
    }

    override fun debug(format: String, arg1: Any, arg2: Any) {
        tag()
        Timber.d(format, arg1, arg2)
    }

    override fun debug(format: String, vararg arguments: Any) {
        tag()
        Timber.d(format, arguments)
    }

    override fun debug(message: String, throwable: Throwable?) {
        tag()
        if (throwable == null) {
            Timber.w(message)
        } else {
            Timber.w(throwable, message)
        }
    }

    override fun info(message: String) {
        info(message, null as Throwable?)
    }

    override fun info(format: String, arg: Any) {
        tag()
        Timber.i(format, arg)
    }

    override fun info(format: String, arg1: Any, arg2: Any) {
        tag()
        Timber.i(format, arg1, arg2)
    }

    override fun info(format: String, vararg arguments: Any) {
        tag()
        Timber.i(format, arguments)
    }

    override fun info(message: String, throwable: Throwable?) {
        tag()
        if (throwable == null) {
            Timber.i(message)
        } else {
            Timber.i(throwable, message)
        }
    }

    override fun error(message: String) {
        error(message, null as Throwable?)
    }

    override fun error(format: String, arg: Any) {
        tag()
        Timber.e(format, arg)
    }

    override fun error(format: String, arg1: Any, arg2: Any) {
        tag()
        Timber.e(format, arg1, arg2)
    }

    override fun error(format: String, vararg arguments: Any) {
        tag()
        Timber.e(format, arguments)
    }

    override fun error(message: String, throwable: Throwable?) {
        tag()
        if (throwable == null) {
            Timber.e(message)
        } else {
            Timber.e(throwable, message)
        }
    }

    private fun tag() {
        Timber.tag(cls.simpleName)
    }

}