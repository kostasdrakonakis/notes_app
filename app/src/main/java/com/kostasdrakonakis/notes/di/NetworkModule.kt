package com.kostasdrakonakis.notes.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kostasdrakonakis.notes.BuildConfig
import com.kostasdrakonakis.notes.network.api.NotesApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single<Gson> {
        val gsonBuilder = GsonBuilder()
        return@single gsonBuilder.create()
    }

    single {
        val loggingInterceptor = get<Interceptor>()
        val httpClientBuilder = OkHttpClient().newBuilder()
        httpClientBuilder.connectTimeout(5000L, TimeUnit.MILLISECONDS)
        httpClientBuilder.readTimeout(3000L, TimeUnit.MILLISECONDS)
        httpClientBuilder.addInterceptor(loggingInterceptor)
        return@single httpClientBuilder.build()
    }

    single<Interceptor?> {
        if (!BuildConfig.DEBUG) return@single null
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return@single httpLoggingInterceptor
    }

    single<Retrofit> {
        return@single Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single<NotesApi> {
        val retrofit = get<Retrofit>()
        return@single retrofit.create(NotesApi::class.java)
    }
}