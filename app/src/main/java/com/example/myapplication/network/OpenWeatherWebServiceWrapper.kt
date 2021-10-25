package com.example.myapplication.network

import com.example.myapplication.network.OpenWeatherWebService
import com.example.myapplication.network.model.open_weather.OpenWeatherDtoMapper

open class OpenWeatherWebServiceWrapper(
    val webService: OpenWeatherWebService,
    val mapper: OpenWeatherDtoMapper
) {

}