package com.example.myapplication.network.model.open_weather

import com.google.gson.annotations.SerializedName

data class Clouds (
    @SerializedName("all")
    val all: Int
)