package com.kostasdrakonakis.notes.logger

import android.util.Log
import com.google.common.truth.Truth.assertThat
import org.robolectric.shadows.ShadowLog

class LogAssert(private val items: List<ShadowLog.LogItem>) {
    private var index: Int = 1

    fun hasVerboseMessage(tag: String, message: String): LogAssert {
        return hasMessage(Log.VERBOSE, tag, message)
    }

    fun hasVerboseExceptionMessage(tag: String, message: String, exception: Throwable): LogAssert {
        return hasExceptionMessage(Log.VERBOSE, tag, message, exception)
    }

    fun hasDebugMessage(tag: String, message: String): LogAssert {
        return hasMessage(Log.DEBUG, tag, message)
    }

    fun hasDebugExceptionMessage(tag: String, message: String, exception: Throwable): LogAssert {
        return hasExceptionMessage(Log.DEBUG, tag, message, exception)
    }

    fun hasInfoMessage(tag: String, message: String): LogAssert {
        return hasMessage(Log.INFO, tag, message)
    }

    fun hasInfoExceptionMessage(tag: String, message: String, exception: Throwable): LogAssert {
        return hasExceptionMessage(Log.INFO, tag, message, exception)
    }

    fun hasWarnMessage(tag: String, message: String): LogAssert {
        return hasMessage(Log.WARN, tag, message)
    }

    fun hasWarnExceptionMessage(tag: String, message: String, exception: Throwable): LogAssert {
        return hasExceptionMessage(Log.WARN, tag, message, exception)
    }

    fun hasErrorMessage(tag: String, message: String): LogAssert {
        return hasMessage(Log.ERROR, tag, message)
    }

    fun hasErrorExceptionMessage(tag: String, message: String, exception: Throwable): LogAssert {
        return hasExceptionMessage(Log.ERROR, tag, message, exception)
    }

    fun hasAssertMessage(tag: String, message: String): LogAssert {
        return hasMessage(Log.ASSERT, tag, message)
    }

    fun hasAssertExceptionMessage(tag: String, message: String, exception: Throwable): LogAssert {
        return hasExceptionMessage(Log.ASSERT, tag, message, exception)
    }

    companion object {
        fun assertLog(): LogAssert {
            return LogAssert(ShadowLog.getLogs())
        }
    }

    private fun hasMessage(priority: Int, tag: String, message: String): LogAssert {
        val item = items[index]
        assertThat(item.type).isEqualTo(priority)
        assertThat(item.tag).isEqualTo(tag)
        assertThat(item.msg).isEqualTo(message)
        return this
    }

    private fun hasExceptionMessage(
        priority: Int,
        tag: String,
        message: String,
        exception: Throwable
    ): LogAssert {
        val item = items[index]
        assertThat(item.type).isEqualTo(priority)
        assertThat(item.tag).isEqualTo(tag)
        assertThat(item.msg).startsWith(message)
        assertThat(exception).isInstanceOf(Throwable::class.java)
        return this
    }

    fun hasNoMoreMessages() {
        assertThat(items).hasSize(++index)
    }

}
