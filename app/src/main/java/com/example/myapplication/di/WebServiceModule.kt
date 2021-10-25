package com.example.myapplication.di

import com.example.myapplication.data.constants.Keys
import com.example.myapplication.network.OpenWeatherWebService
import com.example.myapplication.network.WeatherStackWebService
import com.example.myapplication.network.model.open_weather.OpenWeatherDtoMapper
import com.example.myapplication.network.model.weather_stack.WeatherStackDtoMapper
import com.example.myapplication.network.OpenWeatherWebServiceWrapper
import com.example.myapplication.network.WeatherStackWebServiceWrapper
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val webServicesModule = module {
    single { WeatherStackWebServiceWrapper(get(named(Keys.WEATHER_STACK)), get()) }
    single(named(Keys.WEATHER_STACK), createdAtStart = false) {
        get<Retrofit>(named(Keys.WEATHER_STACK_RETROFIT))
            .create(WeatherStackWebService::class.java)
    }
    single { WeatherStackDtoMapper() }

    single { OpenWeatherWebServiceWrapper(get(named(Keys.OPEN_WEATHER)), get()) }
    single(named(Keys.OPEN_WEATHER), createdAtStart = false) {
        get<Retrofit>(named(Keys.OPEN_WEATHER_RETROFIT))
            .create(OpenWeatherWebService::class.java)
    }
    single { OpenWeatherDtoMapper() }
}