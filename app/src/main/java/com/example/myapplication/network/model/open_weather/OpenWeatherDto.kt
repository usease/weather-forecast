package com.example.myapplication.network.model.open_weather

import com.google.gson.annotations.SerializedName

data class OpenWeatherDto(
    @SerializedName("base")
    val base: String,

    @SerializedName("")
    val clouds: Clouds,

    @SerializedName("clouds")
    val cod: Int,

    @SerializedName("coordinates")
    val coordinates: Coordinates,

    @SerializedName("dt")
    val dt: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("main")
    val main: Main,

    @SerializedName("name")
    val name: String,

    @SerializedName("sys")
    val sys: Sys,

    @SerializedName("timezone")
    val timezone: Int,

    @SerializedName("visibility")
    val visibility: Int,

    @SerializedName("weather")
    val weather: List<Weather>,

    @SerializedName("wind")
    val wind: Wind
)