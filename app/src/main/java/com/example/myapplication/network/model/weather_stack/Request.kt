package com.example.myapplication.network.model.weather_stack

import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("language")
    val language: String,

    @SerializedName("query")
    val query: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("unit")
    val unit: String
)