package com.example.myapplication.di

import com.example.myapplication.BuildConfig
import com.example.myapplication.data.constants.Constants
import com.example.myapplication.data.constants.Keys
import com.example.myapplication.utils.LiveDataCallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT_CONNECT = 15L
private const val TIMEOUT_WRITE = 15L
private const val TIMEOUT_READ = 15L

val networkModule = module {
    single { Cache(androidApplication().cacheDir, 10L * 1024 * 1024) }

    single { GsonBuilder().create() }

    single  {
        OkHttpClient.Builder().apply {
            cache(get())
            connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
            readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            })
        }.build()
    }

    single(named(Keys.OPEN_WEATHER_RETROFIT)) {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_API_URL_OPEN_WEATHER)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(get())
            .build()
    }

    single(named(Keys.WEATHER_STACK_RETROFIT)) {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_API_URL_WEATHER_STACK)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(get())
            .build()
    }
}