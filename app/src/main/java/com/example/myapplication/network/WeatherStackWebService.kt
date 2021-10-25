package com.example.myapplication.network

import androidx.lifecycle.LiveData
import com.example.myapplication.data.constants.Keys
import com.example.myapplication.network.model.ApiResponse
import com.example.myapplication.network.model.weather_stack.WeatherStackDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherStackWebService {
    @GET("current")
    fun getCurrentWeatherFor(@Query("query") query: String,
                             @Query("access_key") accessKey: String = Keys.KEY_API_WEATHER_STACK): LiveData<ApiResponse<WeatherStackDto>>
}
