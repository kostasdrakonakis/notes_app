package com.kostasdrakonakis.notes.logger

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.kostasdrakonakis.notes.BuildConfig
import com.kostasdrakonakis.notes.logger.LogAssert.Companion.assertLog
import com.kostasdrakonakis.notes.util.LogUtil
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import timber.log.Timber

@Suppress("PrivatePropertyName")
@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class NoteLoggerTest {
    private val logger: NoteLogger = LogUtil.logger<NoteLoggerTest>()
    private val TAG = NoteLoggerTest::class.java.simpleName
    private val LOGGER_NAME = NoteLoggerTest::class.java.simpleName

    companion object {
        private const val DEBUG_MESSAGE = "DEBUG"
        private const val WARN_MESSAGE = "WARN"
        private const val INFO_MESSAGE = "INFO"
        private const val TRACE_MESSAGE = "TRACE"
        private const val ERROR_MESSAGE = "ERROR"
        private const val ASSERT_MESSAGE = "ASSERT"
    }

    @Before
    fun setUp() {
        LogUtil.initLogger()
    }

    @After
    fun tearDown() {
        Timber.uprootAll()
    }

    @Test
    fun verifyName() {
        assertThat(logger.name).isEqualTo(LOGGER_NAME)
    }

    @Test
    fun isDebugEnabled() {
        assertThat(logger.isDebugEnabled).isEqualTo(BuildConfig.DEBUG)
    }

    @Test
    fun isTraceEnabled() {
        assertThat(logger.isTraceEnabled).isEqualTo(BuildConfig.DEBUG)
    }

    @Test
    fun isInfoEnabled() {
        assertThat(logger.isInfoEnabled).isEqualTo(BuildConfig.DEBUG)
    }

    @Test
    fun isWarnEnabled() {
        assertThat(logger.isWarnEnabled).isEqualTo(BuildConfig.DEBUG)
    }

    @Test
    fun isErrorEnabled() {
        assertThat(logger.isErrorEnabled).isEqualTo(BuildConfig.DEBUG)
    }

    @Test
    fun verifyLogger() {
        assertThat(LogUtil.logger<NoteLoggerTest>()).isInstanceOf(logger.javaClass)
    }

    @Test
    fun verifyDebugMessage() {
        logger.debug(DEBUG_MESSAGE)
        assertLog().hasDebugMessage(TAG, DEBUG_MESSAGE).hasNoMoreMessages()
    }

    @Test
    fun verifyDebugExceptionMessage() {
        val exception = Throwable(DEBUG_MESSAGE)
        logger.debug(DEBUG_MESSAGE, exception as Throwable?)
        assertLog().hasDebugExceptionMessage(TAG, DEBUG_MESSAGE, exception).hasNoMoreMessages()
    }

    @Test
    fun verifyWarningMessage() {
        logger.warn(WARN_MESSAGE)
        assertLog().hasWarnMessage(TAG, WARN_MESSAGE).hasNoMoreMessages()
    }

    @Test
    fun verifyWarningExceptionMessage() {
        val exception = Throwable(WARN_MESSAGE)
        logger.warn(WARN_MESSAGE, exception as Throwable?)
        assertLog().hasWarnExceptionMessage(TAG, WARN_MESSAGE, exception).hasNoMoreMessages()
    }

    @Test
    fun verifyInfoMessage() {
        logger.info(INFO_MESSAGE)
        assertLog().hasInfoMessage(TAG, INFO_MESSAGE).hasNoMoreMessages()
    }

    @Test
    fun verifyInfoExceptionMessage() {
        val exception = Throwable(INFO_MESSAGE)
        logger.info(INFO_MESSAGE, exception as Throwable?)
        assertLog().hasInfoExceptionMessage(TAG, INFO_MESSAGE, exception).hasNoMoreMessages()
    }

    @Test
    fun verifyVerboseMessage() {
        logger.trace(TRACE_MESSAGE)
        assertLog().hasVerboseMessage(TAG, TRACE_MESSAGE).hasNoMoreMessages()
    }

    @Test
    fun verifyVerboseExceptionMessage() {
        val exception = Throwable(TRACE_MESSAGE)
        logger.trace(TRACE_MESSAGE, exception as Throwable?)
        assertLog().hasVerboseExceptionMessage(TAG, TRACE_MESSAGE, exception).hasNoMoreMessages()
    }

    @Test
    fun verifyErrorMessage() {
        logger.error(ERROR_MESSAGE)
        assertLog().hasErrorMessage(TAG, ERROR_MESSAGE).hasNoMoreMessages()
    }

    @Test
    fun verifyErrorExceptionMessage() {
        val exception = Throwable(ERROR_MESSAGE)
        logger.error(ERROR_MESSAGE, exception as Throwable?)
        assertLog().hasErrorExceptionMessage(TAG, ERROR_MESSAGE, exception).hasNoMoreMessages()
    }

    @Test
    fun verifyAssertMessage() {
        logger.assert(ASSERT_MESSAGE)
        assertLog().hasAssertMessage(TAG, ASSERT_MESSAGE).hasNoMoreMessages()
    }

    @Test
    fun verifyAssertExceptionMessage() {
        val exception = Throwable(ASSERT_MESSAGE)
        logger.assert(ASSERT_MESSAGE, exception as Throwable?)
        assertLog().hasAssertExceptionMessage(TAG, ASSERT_MESSAGE, exception).hasNoMoreMessages()
    }
}
