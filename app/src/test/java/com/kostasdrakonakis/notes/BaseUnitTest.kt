package com.kostasdrakonakis.notes

import com.kostasdrakonakis.notes.di.serviceModule
import com.kostasdrakonakis.notes.di.testManagersModule
import com.kostasdrakonakis.notes.di.testNetworkModule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(JUnit4::class)
open class BaseUnitTest : KoinTest {
    private val mockWebServer by inject<MockWebServer>()

    @Before
    open fun setUp() {
        startKoin { modules(arrayListOf(testNetworkModule, testManagersModule, serviceModule)) }
        mockWebServer.start()
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
        stopKoin()
    }

    protected fun enqueue(response: MockResponse) {
        mockWebServer.enqueue(response)
    }

    protected fun takeRequest(): RecordedRequest {
        return mockWebServer.takeRequest()
    }

    companion object {
        internal const val LIST_RESPONSE = "LIST_RESPONSE"
        internal const val DELETE_LIST_RESPONSE = "DELETE_LIST_RESPONSE"
        internal const val NOTE_RESPONSE = "NOTE_RESPONSE"
        internal const val UPDATE_RESPONSE = "UPDATE_RESPONSE"
    }
}
