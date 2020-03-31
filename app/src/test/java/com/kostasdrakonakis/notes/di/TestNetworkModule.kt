package com.kostasdrakonakis.notes.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kostasdrakonakis.notes.network.api.NotesApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val testNetworkModule = module {
    single<Gson> {
        val gsonBuilder = GsonBuilder()
        return@single gsonBuilder.create()
    }

    single {
        return@single OkHttpClient().newBuilder().build()
    }

    single<Retrofit> {
        return@single Retrofit.Builder()
            .baseUrl(get<MockWebServer>().url("/"))
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single {
        return@single MockWebServer()
    }

    single<NotesApi> {
        val retrofit = get<Retrofit>()
        return@single retrofit.create(NotesApi::class.java)
    }
}