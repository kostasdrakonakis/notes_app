package com.kostasdrakonakis.notes.di

import com.kostasdrakonakis.notes.BaseUnitTest
import com.kostasdrakonakis.notes.FileUtils
import okhttp3.mockwebserver.MockResponse
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.net.HttpURLConnection

val serviceModule = module {
    single(named("LIST")) {
        return@single FileUtils.readTestResourceFile("list.json")
    }

    single(named("DELETE_LIST")) {
        return@single FileUtils.readTestResourceFile("deleteList.json")
    }

    single(named("NOTE")) {
        return@single FileUtils.readTestResourceFile("note.json")
    }

    single(named("UPDATE")) {
        return@single FileUtils.readTestResourceFile("update.json")
    }

    single(named(BaseUnitTest.LIST_RESPONSE)) {
        return@single MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(get<String>(named("LIST")))
    }

    single(named(BaseUnitTest.NOTE_RESPONSE)) {
        return@single MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(get<String>(named("NOTE")))
    }

    single(named(BaseUnitTest.UPDATE_RESPONSE)) {
        return@single MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(get<String>(named("UPDATE")))
    }

    single(named(BaseUnitTest.DELETE_LIST_RESPONSE)) {
        return@single MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(get<String>(named("DELETE_LIST")))
    }
}