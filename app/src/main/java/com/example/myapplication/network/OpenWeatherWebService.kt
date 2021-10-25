package com.example.myapplication.network

import androidx.lifecycle.LiveData
import com.example.myapplication.data.constants.Keys
import com.example.myapplication.network.model.ApiResponse
import com.example.myapplication.network.model.open_weather.OpenWeatherDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherWebService {
    @GET("weather")
    fun getCurrentWeatherFor(@Query("q") query: String,
                             @Query("appid") accessKey: String = Keys.KEY_API_OPEN_WEATHER,
                             @Query("units") units: String = "metric"): LiveData<ApiResponse<OpenWeatherDto>>
}