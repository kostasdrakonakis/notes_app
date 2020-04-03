package com.kostasdrakonakis.notes.di

import com.kostasdrakonakis.notes.common.BaseWebServerUnitTest
import okhttp3.mockwebserver.MockResponse
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.net.HttpURLConnection

val serviceModule = module {
    single(named("LIST")) {
        return@single FileUtil.readTestResourceFile("list.json")
    }

    single(named("DELETE_LIST")) {
        return@single FileUtil.readTestResourceFile("deleteList.json")
    }

    single(named("NOTE")) {
        return@single FileUtil.readTestResourceFile("note.json")
    }

    single(named("UPDATE")) {
        return@single FileUtil.readTestResourceFile("update.json")
    }

    single(named(BaseWebServerUnitTest.LIST_RESPONSE)) {
        return@single MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(get<String>(named("LIST")))
    }

    single(named(BaseWebServerUnitTest.NOTE_RESPONSE)) {
        return@single MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(get<String>(named("NOTE")))
    }

    single(named(BaseWebServerUnitTest.UPDATE_RESPONSE)) {
        return@single MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(get<String>(named("UPDATE")))
    }

    single(named(BaseWebServerUnitTest.DELETE_LIST_RESPONSE)) {
        return@single MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(get<String>(named("DELETE_LIST")))
    }
}

object FileUtil {
    fun readTestResourceFile(fileName: String): String {
        val fileInputStream = javaClass.classLoader?.getResourceAsStream(fileName)
        return fileInputStream?.bufferedReader()?.readText() ?: ""
    }
}