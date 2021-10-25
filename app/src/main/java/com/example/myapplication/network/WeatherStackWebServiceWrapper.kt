package com.example.myapplication.network

import com.example.myapplication.network.WeatherStackWebService
import com.example.myapplication.network.model.weather_stack.WeatherStackDtoMapper

open class WeatherStackWebServiceWrapper (
    val webService: WeatherStackWebService,
    val mapper: WeatherStackDtoMapper
) {

}