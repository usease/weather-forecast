package com.example.myapplication.network.model.open_weather

import com.example.myapplication.domain.util.DomainMapper
import com.example.myapplication.db.entities.Weather

class OpenWeatherDtoMapper: DomainMapper<OpenWeatherDto, Weather> {
    override fun mapToDomainModel(model: OpenWeatherDto): Weather {
        return Weather(
            model.name,
            model.main.temperature.toInt(),
            convertMeterPerSecondToKilometersPerHour(model.wind.speed),
            System.currentTimeMillis()
        )
    }

    /**
     * Open Weather returns wind speed in meters per second. However, our domain model [Weather] considers its [windSpeed] value as kilometers per hour.
     * Below function shall convert Open Weather's meters per second to kilometers per hour.
     */
    private fun convertMeterPerSecondToKilometersPerHour(meterPerSecond: Double): Int {
        return (meterPerSecond * 3.6).toInt()
    }
}