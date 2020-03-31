package com.kostasdrakonakis.notes

import com.google.gson.GsonBuilder
import com.kostasdrakonakis.notes.managers.api.ApiProvider
import com.kostasdrakonakis.notes.network.api.NotesApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
open class BaseUnitTest {

    @Mock
    protected lateinit var apiProvider: ApiProvider
    protected lateinit var notesApi: NotesApi
    protected val mockWebServer = MockWebServer()

    @Before
    open fun setUp() {
        mockWebServer.start()
        notesApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient().newBuilder().build())
            .build()
            .create(NotesApi::class.java)
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(apiProvider.notesApi).thenReturn(notesApi)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
