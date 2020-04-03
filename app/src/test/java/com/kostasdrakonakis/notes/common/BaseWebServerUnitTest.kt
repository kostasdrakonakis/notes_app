package com.kostasdrakonakis.notes.common

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.koin.test.inject

open class BaseWebServerUnitTest : BaseUnitTest() {
    private val mockWebServer by inject<MockWebServer>()

    override fun setUp() {
        super.setUp()
        mockWebServer.start()
    }

    override fun tearDown() {
        super.tearDown()
        mockWebServer.shutdown()
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
