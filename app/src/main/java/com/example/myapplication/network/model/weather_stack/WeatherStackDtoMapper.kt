package com.example.myapplication.network.model.weather_stack

import com.example.myapplication.db.entities.Weather
import com.example.myapplication.domain.util.DomainMapper

class WeatherStackDtoMapper: DomainMapper<WeatherStackDto, Weather> {
    override fun mapToDomainModel(model: WeatherStackDto): Weather {
        return Weather(
            model.location.name,
            model.weatherCurrent.temperature, // Weather Stack by default returns temperature in Celsius
            model.weatherCurrent.windSpeed, // Weather Stack by default returns wind speed in kilometers per hour.
            System.currentTimeMillis()
        )
    }
}