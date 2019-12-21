package com.kostasdrakonakis.notes.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kostasdrakonakis.notes.BuildConfig
import com.kostasdrakonakis.notes.network.api.NotesApi
import dagger.Module
import dagger.Provides
import io.reactivex.annotations.Nullable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(@Nullable loggingInterceptor: Interceptor?): OkHttpClient {
        val httpClientBuilder = OkHttpClient().newBuilder()
        httpClientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        httpClientBuilder.readTimeout(SOCKET_TIMEOUT, TimeUnit.MILLISECONDS)
        if (loggingInterceptor != null) httpClientBuilder.addInterceptor(loggingInterceptor)
        return httpClientBuilder.build()
    }

    @Provides
    @Singleton
    @Nullable
    fun provideLoggingInterceptor(): Interceptor? {
        if (!BuildConfig.DEBUG) return null

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideEndpoint(retrofit: Retrofit): NotesApi {
        return retrofit.create<NotesApi>(NotesApi::class.java)
    }

    companion object {
        private const val CONNECT_TIMEOUT = 5000L
        private const val SOCKET_TIMEOUT = 30000L
    }
}
