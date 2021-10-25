package com.example.myapplication.di

import com.example.myapplication.repository.WeatherRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single { WeatherRepository(get(), get(), get(), get()) }
}