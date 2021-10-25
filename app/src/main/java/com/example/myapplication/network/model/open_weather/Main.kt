package com.example.myapplication.network.model.open_weather

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("pressure")
    val pressure: Int,

    @SerializedName("temp")
    val temperature: Double,

    @SerializedName("temp_max")
    val temperatureMax: Double,

    @SerializedName("temp_min")
    val temperatureMin: Double
)