package com.kostasdrakonakis.notes.logger

import android.util.Log
import com.google.common.truth.FailureMetadata
import com.google.common.truth.Subject
import com.google.common.truth.Subject.Factory
import com.google.common.truth.Truth.assertAbout
import com.google.common.truth.Truth.assertThat
import com.kostasdrakonakis.notes.BuildConfig
import org.robolectric.shadows.ShadowLog
import kotlin.reflect.KClass

class LogAssertSubject(
    metadata: FailureMetadata?,
    private val actual: NoteLogger?,
    private val items: List<ShadowLog.LogItem>
) : Subject(metadata, actual) {

    private var index: Int = 0

    fun hasVerboseMessage(tag: String, message: String): LogAssertSubject {
        return hasMessage(Log.VERBOSE, tag, message)
    }

    fun hasVerboseExceptionMessage(
        tag: String,
        message: String,
        exception: Throwable
    ): LogAssertSubject {
        return hasExceptionMessage(Log.VERBOSE, tag, message, exception)
    }

    fun hasDebugMessage(tag: String, message: String): LogAssertSubject {
        return hasMessage(Log.DEBUG, tag, message)
    }

    fun hasDebugExceptionMessage(
        tag: String,
        message: String,
        exception: Throwable
    ): LogAssertSubject {
        return hasExceptionMessage(Log.DEBUG, tag, message, exception)
    }

    fun hasInfoMessage(tag: String, message: String): LogAssertSubject {
        return hasMessage(Log.INFO, tag, message)
    }

    fun hasInfoExceptionMessage(
        tag: String,
        message: String,
        exception: Throwable
    ): LogAssertSubject {
        return hasExceptionMessage(Log.INFO, tag, message, exception)
    }

    fun hasWarnMessage(tag: String, message: String): LogAssertSubject {
        return hasMessage(Log.WARN, tag, message)
    }

    fun hasWarnExceptionMessage(
        tag: String,
        message: String,
        exception: Throwable
    ): LogAssertSubject {
        return hasExceptionMessage(Log.WARN, tag, message, exception)
    }

    fun hasErrorMessage(tag: String, message: String): LogAssertSubject {
        return hasMessage(Log.ERROR, tag, message)
    }

    fun hasErrorExceptionMessage(
        tag: String,
        message: String,
        exception: Throwable
    ): LogAssertSubject {
        return hasExceptionMessage(Log.ERROR, tag, message, exception)
    }

    fun hasAssertMessage(tag: String, message: String): LogAssertSubject {
        return hasMessage(Log.ASSERT, tag, message)
    }

    fun hasAssertExceptionMessage(
        tag: String,
        message: String,
        exception: Throwable
    ): LogAssertSubject {
        return hasExceptionMessage(Log.ASSERT, tag, message, exception)
    }

    fun isInstance(clazz: KClass<NoteLogger>) {
        assertThat(actual?.javaClass?.simpleName).isEqualTo(clazz.java.simpleName)
    }

    fun hasNoMoreMessages() {
        assertThat(items).hasSize(++index)
    }

    fun isDebugEnabled() {
        assertThat(actual?.isDebugEnabled).isEqualTo(BuildConfig.DEBUG)
    }

    fun isErrorEnabled() {
        assertThat(actual?.isErrorEnabled).isEqualTo(BuildConfig.DEBUG)
    }

    fun isTraceEnabled() {
        assertThat(actual?.isTraceEnabled).isEqualTo(BuildConfig.DEBUG)
    }

    fun isInfoEnabled() {
        assertThat(actual?.isInfoEnabled).isEqualTo(BuildConfig.DEBUG)
    }

    fun isWarnEnabled() {
        assertThat(actual?.isWarnEnabled).isEqualTo(BuildConfig.DEBUG)
    }

    fun hasNameEqualTo(name: String) {
        assertThat(actual?.name).isEqualTo(name)
    }

    private fun hasMessage(priority: Int, tag: String, message: String): LogAssertSubject {
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
    ): LogAssertSubject {
        val item = items[index]
        assertThat(item.type).isEqualTo(priority)
        assertThat(item.tag).isEqualTo(tag)
        assertThat(item.msg).startsWith(message)
        assertThat(exception).isInstanceOf(Throwable::class.java)
        return this
    }

    companion object {
        fun assertThat(actual: NoteLogger?): LogAssertSubject {
            return assertAbout(logFactory()).that(actual)
        }

        private fun logFactory(): Factory<LogAssertSubject, NoteLogger> {
            return Factory<LogAssertSubject, NoteLogger> { metadata, actual ->
                LogAssertSubject(metadata, actual, ShadowLog.getLogs())
            }
        }
    }
}
