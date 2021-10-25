package com.example.myapplication.network.model.weather_stack

import com.google.gson.annotations.SerializedName

data class WeatherStackDto(
    @SerializedName("current")
    val weatherCurrent: WeatherCurrent,

    @SerializedName("location")
    val location: Location,

    @SerializedName("request")
    val request: Request
)